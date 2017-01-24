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
import model.Local;

/**
 *
 * @author RodolfoSaldanha 
 */
public class LocalDAO extends DAO<Local>{
    
    static String allQuery = "SELECT lid, placeName, telephone FROM local";
    
    static String setId = "SELECT lid FROM local WHERE placeName=?";
    
    static String infoQuery = "SELECT login, pwd, fullName, photoPath, cpf, rg, nameTag, email, address, logradouro, complemento, bairro, cep, city, state, telResidencial, dBirth, estadoCivil, escolaridade, profession, instOrigem FROM usuario WHERE";
    
    //static String userTypeQuery = "SELECT userType FROM usuario WHERE login=? AND pwd=md5(?)";

    static String createQuery = "INSERT INTO local(placeName, telephone, address, logradouro, complemento, bairro, cep, city, state, coordenates)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING lid";

    static String deleteQuery = "DELETE FROM local WHERE lid=?";

    static String readQuery = "SELECT telephone, placeName FROM local WHERE lid=?";
    
    //static String updateQuery = "UPDATE usuario SET login=?, pwd=?, fullName=?, photoPath=?, cpf=?, rg=?, nameTag=?, email=?, address=?, logradouro=?, complemento=?, bairro=?, cep=?, city=?, state=?, telResidencial=?, dBirth=?, estadoCivil=?, escolaridade=?, profession=?, instOrigem=?";

    //static String updateWithPasswordQuery = "UPDATE usuario "
            //+ "SET login=?, fullName=?, password=md5(?) WHERE uid=?";
    
    //static String authenticateQuery = "SELECT uid, fullName FROM usuario WHERE login=? AND pwd=md5(?)";

    public LocalDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Local local) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            
            statement.setString(1, local.getPlaceName());
            statement.setString(2, local.getTelephone());
            statement.setString(3, local.getAddress());
            statement.setString(4, local.getLogradouro());
            statement.setString(5, local.getComplemento());
            statement.setString(6, local.getBairro());
            statement.setString(7, local.getCep());
            statement.setString(8, local.getCity());
            statement.setString(9, local.getState());
            statement.setString(10, local.getCoordenates());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                local.setLid(result.getLong("lid"));
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
    public Local read(Long id) throws SQLException {
        Local local = new Local();

        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    local.setLid(id);
                    local.setTelephone(result.getString("login"));
                    local.setPlaceName(result.getString("placeName"));
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

        return local;
    }

    @Override
    public void update(Local local) throws SQLException {
        String query;
/*
        if (local.getPwd() == null) {
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
            statement.setString(17, usuario.getDBirth());
            statement.setString(18, usuario.getEstadoCivil());
            statement.setString(19, usuario.getEscolaridade());
            statement.setString(20, usuario.getProfession());
            statement.setString(21, usuario.getInstOrigem());

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
    public List<Local> all() throws SQLException {
        List<Local> localList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Local local = new Local();
                local.setLid(result.getLong("lid"));
                local.setTelephone(result.getString("telephone"));
                local.setPlaceName(result.getString("placeName"));

                localList.add(local);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return localList;

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
    
    public void setId(Local local) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(setId);){
            statement.setString(1, local.getPlaceName());    
            
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    local.setLid(result.getLong("lid"));
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
