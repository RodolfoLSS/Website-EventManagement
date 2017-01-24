/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.EntPromotora;
/**
 *
 * @author RodolfoSaldanha
 */
public class EntPromotoraDAO extends DAO<EntPromotora>{
    
    static String allQuery = "SELECT eid, entName, description FROM entPromotora";
    
    static String setId = "SELECT eid FROM entPromotora WHERE entName=?";
    
    //static String userTypeQuery = "SELECT userType FROM usuario WHERE login=? AND pwd=md5(?)";

    static String createQuery = "INSERT INTO entPromotora(entName, description)"
            + "VALUES (?, ?) RETURNING eid";

    static String deleteQuery = "DELETE FROM entPromotora WHERE eid=?";

    static String readQuery = "SELECT description, entName FROM entPromotora WHERE eid=?";
    
    //static String updateQuery = "UPDATE usuario SET login=?, fullName=? WHERE uid=?";

    //static String updateWithPasswordQuery = "UPDATE usuario "
            //+ "SET login=?, fullName=?, password=md5(?) WHERE uid=?";
    
    //static String authenticateQuery = "SELECT uid, fullName FROM usuario WHERE login=? AND pwd=md5(?)";

    public EntPromotoraDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(EntPromotora entPromotora) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, entPromotora.getEntName());
            statement.setString(2, entPromotora.getDescription());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                entPromotora.setEid(result.getLong("eid"));
            }

            result.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().contains("uk_usuario_login")) {
                throw new SQLException("Erro ao inserir usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    @Override
    public EntPromotora read(Long id) throws SQLException {
        EntPromotora entPromotora = new EntPromotora();
        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    //entPromotora.setEventid(id);
                    //entPromotora.setDescription(result.getString("description"));
                    //entPromotora.setEventName(result.getString("eventName"));
                } else {
                    throw new SQLException("Erro ao visualizar: usuário não encontrado.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao visualizar: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar usuário.");
            }
        }

        return entPromotora;
    }

    @Override
    public void update(EntPromotora entPromotora) throws SQLException {
        String query;
/*
        if (evento.getPwd() == null) {
            query = updateQuery;
        } else {
            query = updateWithPasswordQuery;
        }

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getFullName());
            
            statement.setString(4, usuario.getPhotoPath());
            statement.setString(5, usuario.getCpf());
            statement.setString(6, usuario.getRg());
            statement.setString(7, usuario.getNameTag());
            statement.setString(8, usuario.getEmail());
            statement.setString(9, usuario.getAddress());
            statement.setString(10, usuario.getLogradouro());
            statement.setString(11, usuario.getComplemento());
            statement.setString(12, usuario.getBairro());
            statement.setString(13, usuario.getCep());
            statement.setString(14, usuario.getCity());
            statement.setString(15, usuario.getState());
            statement.setString(16, usuario.getTelResidencial());
            statement.setString(17, usuario.getDBirth());
            statement.setString(18, usuario.getEstadoCivil());
            statement.setString(19, usuario.getEscolaridade());
            statement.setString(20, usuario.getProfession());
            statement.setString(21, usuario.getInstOrigem());
            statement.setInt(22, usuario.getUserType());

            if (usuario.getPwd() == null) {
                statement.setLong(3, usuario.getUid());
            } else {
                statement.setString(3, usuario.getPwd());
                statement.setLong(4, usuario.getUid());
            }

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao editar: usuário não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uk_usuario_login")) {
                throw new SQLException("Erro ao editar usuário: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar usuário: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar usuário.");
            }
        }*/
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
            statement.setLong(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: usuário não encontrado.");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            if (ex.getMessage().equals("Erro ao excluir: usuário não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir usuário.");
            }
        }

    }

    @Override
    public List<EntPromotora> all() throws SQLException {
        List<EntPromotora> entPromotoraList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                EntPromotora entPromotora = new EntPromotora();
                entPromotora.setEid(result.getLong("eid"));
                entPromotora.setDescription(result.getString("description"));
                entPromotora.setEntName(result.getString("entName"));

                entPromotoraList.add(entPromotora);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return entPromotoraList;

    }
/*
    public void authenticate(Usuario usuario) throws SQLException, SecurityException {
        try (PreparedStatement statement = connection.prepareStatement(authenticateQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getPwd());

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setUid(result.getLong("uid"));
                    usuario.setFullName(result.getString("fullName"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao autenticar usuário.");
        }
        
        try (PreparedStatement statement = connection.prepareStatement(userTypeQuery);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getPwd());

            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setUserType(result.getInt("userType"));
                } else {
                    throw new SecurityException("Erro ao autenticar usuário - tipo do usuário.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao autenticar usuário.");
        }
    }*/
    
    public void setId(EntPromotora entPromotora) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(setId);){
            statement.setString(1, entPromotora.getEntName());    
            
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    entPromotora.setEid(result.getLong("eid"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }
    }
}
