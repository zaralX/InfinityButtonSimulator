package ru.zaralx.utils.zModules.database;

import ru.zaralx.InfinityButtonSimulator;
import ru.zaralx.utils.zModules.configs.defaultPlayerStats;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;

public class database {
    private Connection _connection;

    public Connection getConnection(){
        return _connection;
    }

    public boolean isConnected(){
        return _connection != null;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        InfinityButtonSimulator.getInstance().getLogger().warning("Connecting..");
        Class.forName("com.mysql.cj.jdbc.Driver");
        File dataFolder = new File(InfinityButtonSimulator.getInstance().getDataFolder(), "game.db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                InfinityButtonSimulator.getInstance().getLogger().log(Level.SEVERE, "File write error: game.db");
            }
        }
        _connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
    }

    public void disconnect(){
        if(isConnected()){
            try {
                _connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet executeNoNext(String query) throws SQLException {
        Statement statement = _connection.createStatement();
        return statement.executeQuery(query);
    }

    public ResultSet execute(String query) throws SQLException, ClassNotFoundException {
        if(!isConnected() || !_connection.isValid(10)) connect();
        Statement statement = _connection.createStatement();
        ResultSet result = statement.executeQuery(query);
        if(result.next()) return result;
        else return null;
    }

    public void executeUpdate(String query) throws SQLException, ClassNotFoundException {
        if(!isConnected() || !_connection.isValid(10)) connect();
        Statement statement = _connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    public void init() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS player_stats (`player_uuid` varchar(36) NOT NULL,";
            for (int i = 0; i < defaultPlayerStats.defaults.size(); i++) {
                String add = query + "`"+ defaultPlayerStats.defaults.get(i).getFirst()+"` ";
                if (String.class.equals(defaultPlayerStats.defaults.get(i).getSecond().getClass())) {
                    add = add + "text";
                } else if (Double.class.equals(defaultPlayerStats.defaults.get(i).getSecond().getClass())) {
                    add = add + "double";
                } else if (Integer.class.equals(defaultPlayerStats.defaults.get(i).getSecond().getClass())) {
                    add = add + "int";
                } else {
                    System.err.println("[DATABASE WARN] Not found type: " + defaultPlayerStats.defaults.get(i).getSecond().getClass().getName());
                }
                query = add + " NOT NULL,";
            }
            query = query + "PRIMARY KEY (`player_uuid`));";
            executeUpdate(query);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}