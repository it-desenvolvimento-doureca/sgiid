package pt.example.dao;

public interface GenericDao<T, PK> {
    T create(T t);
    T read(PK id);
    T update(T t);
    void delete(T t);
}