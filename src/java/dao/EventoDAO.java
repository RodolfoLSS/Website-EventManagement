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
import model.Evento;
/**
 *
 * @author RodolfoSaldanha
 */
public class EventoDAO extends DAO<Evento>{
    
    static String allQuery = "SELECT eventid, eventName, fk_entPromotora_evento, beginDate, endDate FROM evento";
    
    //static String userTypeQuery = "SELECT userType FROM usuario WHERE login=? AND pwd=md5(?)";

    static String createQuery = "INSERT INTO evento(eventName, description, impInformation, price, beginDate, endDate, beginTime, endTime, weekdays, eventType, fk_usuario_evento, fk_local_evento, fk_entPromotora_evento)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING eventid";

    static String deleteQuery = "DELETE FROM evento WHERE eventid=?";

    static String readQuery = "SELECT description, eventName FROM evento WHERE eventid=?";
    
    static String updateQuery = "UPDATE evento SET eventName=?, description=?, impInformation=?, price=?, beginDate=?, endDate=?, beginTime=?, endTime=?, weekdays=?, eventType=?, fk_usuario_evento=?, fk_local_evento=?, fk_entPromotora_evento=? WHERE eventid=?";

    //static String updateWithPasswordQuery = "UPDATE usuario "
            //+ "SET login=?, fullName=?, password=md5(?) WHERE uid=?";
    
    //static String authenticateQuery = "SELECT uid, fullName FROM usuario WHERE login=? AND pwd=md5(?)";

    public EventoDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Evento evento) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(createQuery);) {
            statement.setString(1, evento.getEventName());
            statement.setString(2, evento.getDescription());
            statement.setString(3, evento.getImpInformation());
            statement.setInt(4, evento.getPrice());
            statement.setString(5, evento.getBeginDate());
            statement.setString(6, evento.getEndDate());
            statement.setString(7, evento.getBeginTime());
            statement.setString(8, evento.getEndTime());
            statement.setString(9, evento.getWeekdays());
            statement.setInt(10, evento.getEventType());
            statement.setLong(11, evento.getFk_usuario_evento());
            statement.setLong(12, evento.getFk_local_evento());
            statement.setLong(13, evento.getFk_entPromotora_evento());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                evento.setEventid(result.getLong("eventid"));
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
    public Evento read(Long id) throws SQLException {
        Evento evento = new Evento();
        try (PreparedStatement statement = connection.prepareStatement(readQuery);) {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    evento.setEventid(id);
                    evento.setDescription(result.getString("description"));
                    evento.setEventName(result.getString("eventName"));
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

        return evento;
    }

    @Override
    public void update(Evento evento) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(updateQuery);) {
            
            statement.setString(1, evento.getEventName());
            statement.setString(2, evento.getDescription());
            statement.setString(3, evento.getImpInformation());
            statement.setInt(4, evento.getPrice());
            statement.setString(5, evento.getBeginDate());
            statement.setString(6, evento.getEndDate());
            statement.setString(7, evento.getBeginTime());
            statement.setString(8, evento.getEndTime());
            statement.setString(9, evento.getWeekdays());
            statement.setInt(10, evento.getEventType());
            statement.setLong(11, ((long)23));
            statement.setLong(12, ((long)8));
            statement.setLong(13, ((long)2));

            
                statement.setLong(14, evento.getEventid());

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
    public List<Evento> all() throws SQLException {
        List<Evento> eventoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(allQuery);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Evento evento = new Evento();
                evento.setEventid(result.getLong("eventid"));
                evento.setEventName(result.getString("eventName"));
                evento.setFk_entPromotora_evento(result.getLong("fk_entPromotora_evento"));
                evento.setBeginDate(result.getString("beginDate"));
                evento.setEndDate(result.getString("endDate"));

                eventoList.add(evento);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

            throw new SQLException("Erro ao listar usuários.");
        }

        return eventoList;

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
