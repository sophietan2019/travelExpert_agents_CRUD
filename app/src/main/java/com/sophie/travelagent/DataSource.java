package com.sophie.travelagent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sophie Tan on 12/10/2020.
 */


public class DataSource {
    //fields
    private Context context;
    private SQLiteDatabase db;
    private DBHelper helper;


    public static final String QUERY_AGENTS_BY_AGENT_ID="SELECT * FROM agents WHERE AgentId=?";
    public static final String[] ALL_COLUMNS= {"AgentId","AgtFirstName","AgtMiddleInitial",
            "AgtLastName","AgtBusPhone","AgtEmail"};


    //constructor
    public DataSource(Context context) {
        this.context = context;
        helper=new DBHelper(context);
        db=helper.getWritableDatabase();
        Log.d("Sophie","context: "+context);
    }

    //methods

    public Agent getAgentByID(int agtId){

        String [] args= {String.valueOf(agtId)};
        Cursor cursor=db.rawQuery(QUERY_AGENTS_BY_AGENT_ID,args);
        cursor.moveToNext();
        return new Agent(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));
    }

    public ArrayList<Agent> getAllAgent(){
        ArrayList<Agent> agents=new ArrayList<>();

       Cursor cursor=db.query("agents",ALL_COLUMNS,null,null,
                null,null, null);

        while(cursor.moveToNext()){
           agents.add(new Agent(cursor.getInt(0),cursor.getString(1),
                    cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5)));
        }

        return agents;
    }

    public long addAgent(Agent agent){
        ContentValues contentValues=new ContentValues();
        contentValues.put("AgtFirstName",agent.getAgtFirstName());
        contentValues.put("AgtMiddleInitial",agent.getAgtMiddleInitial());
        contentValues.put("AgtLastName",agent.getAgtLastName());
        contentValues.put("AgtBusPhone",agent.getAgtBusPhone());
        contentValues.put("AgtEmail",agent.getAgtEmail());
        return db.insert("agents",null,contentValues);

    }

    public long  updateAgent(Agent agent){

        String [] args={String.valueOf(agent.getAgentId())};
        String where="AgentId = ?";
        ContentValues contentValues=new ContentValues();

        contentValues.put("AgtFirstName",agent.getAgtFirstName());
        contentValues.put("AgtMiddleInitial",agent.getAgtMiddleInitial());
        contentValues.put("AgtLastName",agent.getAgtLastName());
        contentValues.put("AgtBusPhone",agent.getAgtBusPhone());
        contentValues.put("AgtEmail",agent.getAgtEmail());

        return db.update("agent",contentValues,where,args);

    }

    public long  deleteAgent(int agentId){
        String[] args={String.valueOf(agentId)};
        String where="AgentId = ?";
        return db.delete("agents",where,args);
    }



}
