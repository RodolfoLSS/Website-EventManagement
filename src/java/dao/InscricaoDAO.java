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
import model.Inscricao;
import model.Usuario;
/**
 *
 * @author RodolfoSaldanha
 */
public class InscricaoDAO extends DAO<Inscricao>{
    
    static String allQuery = "SELECT eventid, description, eventName FROM evento";
    
    static String partEventoQuery = "SELECT * FROM inscricao WHERE fk_eventid=?";
    
    static String partQuery = "SELECT * FROM usuario WHERE uid=?";
    
    //static String userTypeQuery = "SELECT userType FROM usuario WHERE login=? AND pwd=md5(?)";

    static String createQuery = "INSERT INTO inscricao(payment, getToKnowEvent, descriptiveField, fk_eventid, fk_uid)"
            + "VALUES (?, ?, ?, ?, ?)";

    static String deleteQuery = "DELETE FROM evento WHERE eventid=?";

    static String readQuery = "SELECT description, eventName FROM evento WHERE eventid=?";
    
    //static String updateQuery = "UPDATE usuario SET login=?, fullName=? WHERE uid=?";

    //static String updateWithPasswordQuery = "UPDATE usuario "
            //+ "SET login=?, fullName=?, password=md5(?) WHERE uid=?";
    
    //static String authenticateQuery = "SELECT uid, fullName FROM usuario WHERE login=? AND pwd=md5(?)";

    public InscricaoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Inscricao inscricao) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, inscricao.getPayment());
            statement.setString(2, inscricao.getGetToKnowEvent());
            statement.setString(3, inscricao.getDescriptiveField());
            statement.setLong(4, inscricao.getFk_eventid());
            statement.setLong(5, inscricao.getFk_uid());
            
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
    public Inscricao read(Long id) throws SQLException {
        Inscricao inscricao = new Inscricao();
        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {/*
                    evento.setEventid(id);
                    evento.setDescription(result.getString("description"));
                    evento.setEventName(result.getString("eventName"));*/
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

        return inscricao;
    }

    @Override
    public void update(Inscricao inscricao) throws SQLException {
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
    public List<Inscricao> all() throws SQLException {
        List<Inscricao> inscricaoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Inscricao inscricao = new Inscricao();/*
                inscricao.setEventid(result.getLong("eventid"));
                evento.setDescription(result.getString("description"));
                evento.setEventName(result.getString("eventName"));*/

                inscricaoList.add(inscricao);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return inscricaoList;

    }
    
    public List<Usuario> inscritosList(Long id) throws SQLException {
        List<Inscricao> inscritosList = new ArrayList<>();
        List<Usuario> partList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(partEventoQuery);) {
            statement.setLong(1, id);
            
            try (ResultSet result = statement.executeQuery();) {
                
                while (result.next()) {
                    Inscricao inscricao = new Inscricao();
                    inscricao.setFk_uid(result.getLong("fk_uid"));
                    inscritosList.add(inscricao);
                    
                    try (PreparedStatement state = connection.prepareStatement(partQuery);) {
                        state.setLong(1, inscricao.getFk_uid());
            
                        try (ResultSet r = state.executeQuery();) {
                            while (r.next()) {
                                Usuario usuario= new Usuario();
                                usuario.setFullName(r.getString("fullName"));
                                partList.add(usuario);
                            }
                        }
                    } catch (SQLException ex) {
                         System.err.println(ex.getMessage());
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return partList;

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
    
}
