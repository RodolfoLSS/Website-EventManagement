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
import model.Usuario;

/**
 *
 * @author RodolfoSaldanha 
 */
public class UsuarioDAO extends DAO<Usuario>{
    
    static String allQuery = "SELECT uid, login, fullName FROM usuario";
    
    static String setId = "SELECT uid FROM usuario WHERE pwd=md5(?)";
    
    static String infoQuery = "SELECT login, pwd, fullName, photoPath, cpf, rg, nameTag, email, address, logradouro, complemento, bairro, cep, city, state, telResidencial, dBirth, estadoCivil, escolaridade, profession, instOrigem FROM usuario WHERE";
    
    static String userTypeQuery = "SELECT userType FROM usuario WHERE login=? AND pwd=md5(?)";

    static String createQuery = "INSERT INTO usuario(login, pwd, fullName, photoPath, cpf, rg, nameTag, email, address, logradouro, complemento, bairro, cep, city, state, telResidencial, dBirth, estadoCivil, escolaridade, profession, instOrigem, userType)"
            + "VALUES (?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING uid";

    static String deleteQuery = "DELETE FROM usuario WHERE uid=?";

    static String readQuery = "SELECT login, fullName FROM usuario WHERE uid=?";
    
    static String updateQuery = "UPDATE usuario SET login=?, pwd=md5(?), fullName=?, photoPath=?, cpf=?, rg=?, nameTag=?, email=?, address=?, logradouro=?, complemento=?, bairro=?, cep=?, city=?, state=?, telResidencial=?, dBirth=?, estadoCivil=?, escolaridade=?, profession=?, instOrigem=?, userType=?";

    static String updateWithPasswordQuery = "UPDATE usuario "
            + "SET login=?, pwd=md5(?), fullName=?, photoPath=?, cpf=?, rg=?, nameTag=?, email=?, address=?, logradouro=?, complemento=?, bairro=?, cep=?, city=?, state=?, telResidencial=?, dBirth=?, estadoCivil=?, escolaridade=?, profession=?, instOrigem=?, userType=? WHERE fullName=?";
    
    static String authenticateQuery = "SELECT uid, fullName FROM usuario WHERE login=? AND pwd=md5(?)";

    public UsuarioDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getPwd());
            statement.setString(3, usuario.getFullName());
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

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                usuario.setUid(result.getLong("uid"));
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
    public Usuario read(Long id) throws SQLException {
        Usuario usuario = new Usuario();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setUid(id);
                    usuario.setLogin(result.getString("login"));
                    usuario.setFullName(result.getString("fullName"));
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

        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String query;

        if (usuario.getPwd() == null) {
            query = updateQuery;
        } else {
            query = updateWithPasswordQuery;
        }

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getPwd());
            statement.setString(3, usuario.getFullName());
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
            statement.setString(17, "12/04/1996");
            statement.setString(18, usuario.getEstadoCivil());
            statement.setString(19, usuario.getEscolaridade());
            statement.setString(20, usuario.getProfession());
            statement.setString(21, usuario.getInstOrigem());
            statement.setInt(22, usuario.getUserType());
            
            statement.setString(23, usuario.getFullName());

            //if (usuario.getPwd() == null) {
              //  statement.setLong(3, usuario.getUid());
            //} else {
             //   statement.setString(3, usuario.getPwd());
             //   statement.setLong(4, usuario.getUid());
            //}

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
        }
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
    public List<Usuario> all() throws SQLException {
        List<Usuario> usuarioList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setUid(result.getLong("uid"));
                usuario.setLogin(result.getString("login"));
                usuario.setFullName(result.getString("fullName"));

                usuarioList.add(usuario);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return usuarioList;

    }
    
    public Usuario info(Long id) throws SQLException {
        Usuario usuario = new Usuario();

        try (PreparedStatement statement = connection.prepareStatement(infoQuery);){
            statement.setLong(1, id);    
            
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

            throw new SQLException("Erro ao listar usuários.");
        }

        return usuario;

    }
    
    public void setId(Usuario usuario) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(setId);){
            statement.setString(1, usuario.getPwd());    
            
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    usuario.setUid(result.getLong("uid"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }
    }

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
    }
    
}
