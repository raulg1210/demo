package com.periflus.demo.dao;

import com.periflus.demo.models.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp  implements UsuarioDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }
    @Override
    public boolean verificarCreedenciales(Usuario usuario){
        String query = "FROM Usuario where email = :email  and password = :password";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter( "email", usuario.getEmail())
                .setParameter( "password", usuario.getPassword())
                .getResultList();
        return !lista.isEmpty();
    }
}
