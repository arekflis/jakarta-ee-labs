package pl.edu.pg.eti.kask.ucm.configuration.validation;

import jakarta.faces.context.FacesContext;
import jakarta.validation.MessageInterpolator;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacesResourceBundleLocator implements MessageInterpolator {

    private static final Pattern PARAMETER_PATTERN = Pattern.compile("\\{([^}]+)\\}");

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return interpolate(messageTemplate, context, getLocale());
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        if (messageTemplate == null) {
            return null;
        }

        String resolvedMessage = messageTemplate;
        if (messageTemplate.startsWith("{") && messageTemplate.endsWith("}")) {
            String key = messageTemplate.substring(1, messageTemplate.length() - 1);
            
            Locale effectiveLocale = locale;
            if (FacesContext.getCurrentInstance() != null) {
                effectiveLocale = FacesContext.getCurrentInstance()
                        .getViewRoot()
                        .getLocale();
            }
            
            try {
                ResourceBundle bundle = ResourceBundle.getBundle(
                        "META-INF.validation.ValidationMessages",
                        effectiveLocale,
                        Thread.currentThread().getContextClassLoader()
                );
                if (bundle.containsKey(key)) {
                    resolvedMessage = bundle.getString(key);
                }
            } catch (Exception e) {
                try {
                    ResourceBundle bundle = ResourceBundle.getBundle(
                            "META-INF.validation.ValidationMessages",
                            Locale.ENGLISH,
                            Thread.currentThread().getContextClassLoader()
                    );
                    if (bundle.containsKey(key)) {
                        resolvedMessage = bundle.getString(key);
                    }
                } catch (Exception ex) {
                }
            }
        }
        
        return interpolateParameters(resolvedMessage, context);
    }

    private String interpolateParameters(String message, Context context) {
        if (context.getConstraintDescriptor() == null) {
            return message;
        }

        Map<String, Object> attributes = context.getConstraintDescriptor().getAttributes();
        String result = message;

        Matcher matcher = PARAMETER_PATTERN.matcher(result);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String paramName = matcher.group(1);
            Object paramValue = attributes.get(paramName);
            if (paramValue != null) {
                matcher.appendReplacement(sb, Matcher.quoteReplacement(String.valueOf(paramValue)));
            } else {
                matcher.appendReplacement(sb, matcher.group(0));
            }
        }
        matcher.appendTail(sb);
        result = sb.toString();

        return result;
    }

    private Locale getLocale() {
        if (FacesContext.getCurrentInstance() != null) {
            return FacesContext.getCurrentInstance()
                    .getViewRoot()
                    .getLocale();
        }
        return Locale.getDefault();
    }
}

