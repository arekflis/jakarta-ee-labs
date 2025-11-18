package pl.edu.pg.eti.kask.ucm.interfaces;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}
