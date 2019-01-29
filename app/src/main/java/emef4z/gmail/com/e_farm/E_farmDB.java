package emef4z.gmail.com.e_farm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static android.R.attr.id;

/**
 * Created by USER on 09-Mar-17.
 */

public class E_farmDB {

    public static final String ADMIN_ROLE = "admin";
    public static final String FARMER_ROLE = "farmer";
    public static final String ORG_ROLE = "organisation";

    public static String UN_FARMER = "";
    public static String UN_ADMIN = "";
    public static String UN_ORG = "";
    public static String ID_ORG = "";


        E_farmHelper e_farmHelper;
    public E_farmDB(Context context){
        e_farmHelper = new E_farmHelper(context);
    }





    //Diagnosis Method
    public Cursor diagnosisMethod(E_farmDB dop)
    {
        SQLiteDatabase db = e_farmHelper.getReadableDatabase();

        String[] columns = {e_farmHelper.SYMP_ID, e_farmHelper.DES_ID_FK, e_farmHelper.AFFECTED_CROP, e_farmHelper.DEFECT_SHAPE, e_farmHelper.DEFECT_COLOR,
                e_farmHelper.DEFECT_LOC, e_farmHelper.DEFECT_NATURE, e_farmHelper.DEFECT_PART_AFFECTED, e_farmHelper.DEFECT_ARRG};

        Cursor cursor = db.query(e_farmHelper.SYMPTOMS_TABLE,columns,null,null,null,null,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }
    }




       // Prabeesh Method Working
    public Cursor loginCredentialsMethodFarmer(E_farmDB dop)
    {
        SQLiteDatabase db = e_farmHelper.getReadableDatabase();

        String[] columns = {e_farmHelper.FARMER_ID_COL,e_farmHelper.FARMER_USERN_COL,e_farmHelper.FARMER_PASS_COL,e_farmHelper.FARMER_ROLE_COL};
        //String[] selectionArgs = {username,password};

        Cursor cursor = db.query(e_farmHelper.FARMER_TABLE,columns,null,null,null,null,null);

        if(cursor != null)
        {
            //cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }

    }


    // Prabeesh Method Working
    public Cursor loginCredentialsMethodAdmin(E_farmDB dop)
    {
        SQLiteDatabase db = e_farmHelper.getReadableDatabase();

        String[] columns = {e_farmHelper.ADMIN_ID_COL,e_farmHelper.ADMIN_USERN_COL,e_farmHelper.ADMIN_PASS_COL,e_farmHelper.ADMIN_ROLE_COL};
        //String[] selectionArgs = {username,password};

        Cursor cursor = db.query(e_farmHelper.ADMIN_TABLE,columns,null,null,null,null,null);

        if(cursor != null)
        {
            //cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }

    }



    // Prabeesh Method Working
    public Cursor loginCredentialsMethodOrg(E_farmDB dop)
    {
        SQLiteDatabase db = e_farmHelper.getReadableDatabase();

        String[] columns = {e_farmHelper.ORG_PRI_ID_COL,e_farmHelper.ORG_ID_COL,e_farmHelper.ORG_USERN_COL,e_farmHelper.ORG_PASS_COL,e_farmHelper.FARMER_ROLE_COL};
        //String[] selectionArgs = {username,password};

        Cursor cursor = db.query(e_farmHelper.ORGANISATION_TABLE,columns,null,null,null,null,null);

        if(cursor != null)
        {
           // cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }

    }


     //Admin Insert Method Working
    public long registerAdminMethod(String photo,String fname,String lname,String loc,String cont,String email,String usern,String pass)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //String role = "admin";
        contentValues.put(E_farmHelper.ADMIN_PHOTO_COL,photo);
        contentValues.put(E_farmHelper.ADMIN_FNAME_COL,fname);
        contentValues.put(E_farmHelper.ADMIN_LNAME_COL,lname);
        contentValues.put(E_farmHelper.ADMIN_LOC_COL,loc);
        contentValues.put(E_farmHelper.ADMIN_CONTACTNO_COL,cont);
        contentValues.put(E_farmHelper.ADMIN_EMAIL_COL,email);
        contentValues.put(E_farmHelper.ADMIN_USERN_COL,usern);
        contentValues.put(E_farmHelper.ADMIN_PASS_COL,pass);
        contentValues.put(E_farmHelper.ADMIN_ROLE_COL,ADMIN_ROLE);//role

        long id = db.insert(E_farmHelper.ADMIN_TABLE,null,contentValues);
        return id;

    }



    // All Admin Info Query
    public Cursor allAdminInfoQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.ADMIN_ID_COL, E_farmHelper.ADMIN_FNAME_COL, E_farmHelper.ADMIN_LNAME_COL, E_farmHelper.ADMIN_LOC_COL,
                E_farmHelper.ADMIN_CONTACTNO_COL, E_farmHelper.ADMIN_EMAIL_COL, E_farmHelper.ADMIN_USERN_COL,
                E_farmHelper.ADMIN_PASS_COL, E_farmHelper.ADMIN_ROLE_COL, E_farmHelper.ADMIN_PHOTO_COL};

        Cursor cursor = db.query(E_farmHelper.ADMIN_TABLE,columns,null,null,null,null,null);


        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }
    }



    // A Particular Admin Info Query
    public Cursor an_AdminInfoQuery(String username)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.ADMIN_PHOTO_COL, E_farmHelper.ADMIN_FNAME_COL, E_farmHelper.ADMIN_LNAME_COL, E_farmHelper.ADMIN_LOC_COL,
                E_farmHelper.ADMIN_CONTACTNO_COL, E_farmHelper.ADMIN_EMAIL_COL, E_farmHelper.ADMIN_USERN_COL};

        Cursor cursor = db.query(E_farmHelper.ADMIN_TABLE,columns, E_farmHelper.ADMIN_USERN_COL+" = '"+username+"'",null,null,null,null);



        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }
    }

        //Farmer Insert Method Working
    public long registerFarmerMethod(String photo,String fname,String lname,String loc,String cont,String email,String usern,String pass)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //String role = "farmer";
        contentValues.put(E_farmHelper.FARMER_PHOTO_COL,photo);
        contentValues.put(E_farmHelper.FARMER_FNAME_COL,fname);
        contentValues.put(E_farmHelper.FARMER_LNAME_COL,lname);
        contentValues.put(E_farmHelper.FARMER_LOC_COL,loc);
        contentValues.put(E_farmHelper.FARMER_CONTACTNO_COL,cont);
        contentValues.put(E_farmHelper.FARMER_EMAIL_COL,email);
        contentValues.put(E_farmHelper.FARMER_USERN_COL,usern);
        contentValues.put(E_farmHelper.FARMER_PASS_COL,pass);
        contentValues.put(E_farmHelper.FARMER_ROLE_COL,FARMER_ROLE);//role

        long id = db.insert(E_farmHelper.FARMER_TABLE,null,contentValues);
        return id;

    }


    //All Farmers Info Query
    public Cursor allFarmerInfoQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

     String[] columns = {E_farmHelper.FARMER_ID_COL, E_farmHelper.FARMER_FNAME_COL, E_farmHelper.FARMER_LNAME_COL, E_farmHelper.FARMER_LOC_COL,
             E_farmHelper.FARMER_CONTACTNO_COL, E_farmHelper.FARMER_EMAIL_COL, E_farmHelper.FARMER_USERN_COL,
             E_farmHelper.FARMER_PASS_COL, E_farmHelper.FARMER_ROLE_COL, E_farmHelper.FARMER_PHOTO_COL};

        Cursor cursor = db.query(E_farmHelper.FARMER_TABLE,columns,null,null,null,null,null);



            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }

        }




    //A Paticular Farmer's Info Query
    public Cursor a_FarmerInfoQuery(String username)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.FARMER_PHOTO_COL, E_farmHelper.FARMER_FNAME_COL, E_farmHelper.FARMER_LNAME_COL, E_farmHelper.FARMER_LOC_COL,
                E_farmHelper.FARMER_CONTACTNO_COL, E_farmHelper.FARMER_EMAIL_COL, E_farmHelper.FARMER_USERN_COL};

        Cursor cursor = db.query(E_farmHelper.FARMER_TABLE,columns, E_farmHelper.FARMER_USERN_COL+" = '"+username+"'",null,null,null,null);


        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }

         }



    public String DeleteFarmerData()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        try{
            String DELETE_QUERY= "DELETE FROM "+E_farmHelper.FARMER_TABLE;
            db.execSQL(DELETE_QUERY);

            return "Data Deleted Successfully";
        } catch (Exception ex)
        {
            return "Error!! "+ ex.getMessage();
        }
    }



    //Organisation Insert Method Working
    public long registerOrgMethod(String photo,String orgName,String orgID,String opID,String yoe,
                                  String loc,String cont,String email,String usern,String pass)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //String role ="organisation";
        contentValues.put(E_farmHelper.ORG_PHOTO_COL,photo);
        contentValues.put(E_farmHelper.ORG_NAME_COL,orgName);
        contentValues.put(E_farmHelper.ORG_ID_COL,orgID);
        contentValues.put(E_farmHelper.ORG_OPLIC_ID_COL,opID);
        contentValues.put(E_farmHelper.ORG_YROFEST_COL,yoe);
        contentValues.put(E_farmHelper.ORG_LOC_COL,loc);
        contentValues.put(E_farmHelper.ORG_CONTACTNO_COL,cont);
        contentValues.put(E_farmHelper.ORG_EMAIL_COL,email);
        contentValues.put(E_farmHelper.ORG_USERN_COL,usern);
        contentValues.put(E_farmHelper.ORG_PASS_COL,pass);
        contentValues.put(E_farmHelper.ORG_ROLE_COL,ORG_ROLE);//role

        long id = db.insert(E_farmHelper.ORGANISATION_TABLE,null,contentValues);
        return id;

    }



    //All Organisation Query Method
    public Cursor allOrgInfoQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.ORG_PRI_ID_COL, E_farmHelper.ORG_NAME_COL, E_farmHelper.ORG_ID_COL, E_farmHelper.ORG_OPLIC_ID_COL,
                E_farmHelper.ORG_YROFEST_COL, E_farmHelper.ORG_LOC_COL, E_farmHelper.ORG_CONTACTNO_COL, E_farmHelper.ORG_EMAIL_COL,
                E_farmHelper.ORG_USERN_COL, E_farmHelper.ORG_PASS_COL, E_farmHelper.ORG_PHOTO_COL, E_farmHelper.ORG_ROLE_COL};

        Cursor cursor = db.query(E_farmHelper.ORGANISATION_TABLE,columns,null,null,null,null,null);



            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }

        }




    //A Particular Organisation Query Method
    public Cursor an_OrgInfoQuery(String username)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.ORG_PHOTO_COL, E_farmHelper.ORG_NAME_COL, E_farmHelper.ORG_ID_COL, E_farmHelper.ORG_OPLIC_ID_COL,
                E_farmHelper.ORG_YROFEST_COL, E_farmHelper.ORG_LOC_COL, E_farmHelper.ORG_CONTACTNO_COL, E_farmHelper.ORG_EMAIL_COL,
                E_farmHelper.ORG_USERN_COL};

        Cursor cursor = db.query(E_farmHelper.ORGANISATION_TABLE,columns, E_farmHelper.ORG_USERN_COL+" = '"+username+"'",null,null,null,null);


            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }

        }



    //Farmer Storage Insert Method
    public long registerFarmer_Storage_Method(String orgIdFk,String framerUserN,String prodName, String prodType, String prodQty
            , String stoDur, String stoSpace, String tp_opt)
    {
        //String orgIdFk="SELECT ",framerUserN="SELECT"; //Get Values using SELECT FROM the various tables

        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.ORG_ID_COL_FKEY,orgIdFk);
        contentValues.put(E_farmHelper.FRM_REGISTERED_COL,framerUserN);
        contentValues.put(E_farmHelper.PROD_NAME_COL,prodName);
        contentValues.put(E_farmHelper.PROD_TYPE_COL,prodType);
        contentValues.put(E_farmHelper.PROD_QTY_COL,prodQty);
        contentValues.put(E_farmHelper.STO_DUR_COL,stoDur);
        contentValues.put(E_farmHelper.STO_SPACE_COL,stoSpace);
        contentValues.put(E_farmHelper.STO_TP_OPTION_COL,tp_opt);

        long id = db.insert(E_farmHelper.FARMER_STORAGE_TABLE,null,contentValues);
        return id;

    }



    //All Farmer Storage Query Method
    public Cursor allFarmerStorageQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.ORG_ID_COL_FKEY, E_farmHelper.FRM_REGISTERED_COL, E_farmHelper.PROD_NAME_COL, E_farmHelper.PROD_TYPE_COL,
                E_farmHelper.PROD_QTY_COL, E_farmHelper.STO_DUR_COL, E_farmHelper.STO_SPACE_COL, E_farmHelper.STO_TP_OPTION_COL};

        Cursor cursor = db.query(E_farmHelper.FARMER_STORAGE_TABLE,columns,null,null,null,null,null);


            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }

        }




    //A Particular Farmer Storage Query Method
    public Cursor a_FarmerStorageQuery(String username)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.ORG_NAME_COL, E_farmHelper.PROD_NAME_COL, E_farmHelper.PROD_TYPE_COL,
                E_farmHelper.PROD_QTY_COL, E_farmHelper.STO_DUR_COL, E_farmHelper.STO_SPACE_COL, E_farmHelper.STO_TP_OPTION_COL};

        Cursor cursor = db.query(E_farmHelper.FARMER_STORAGE_TABLE,columns, E_farmHelper.FRM_REGISTERED_COL+" = '"+username+"'",null,null,null,null);

            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }
        }




    public Cursor selectOrgMethod()
    {
        SQLiteDatabase db = e_farmHelper.getReadableDatabase();

        String selOrg_Query =

                "SELECT "+ E_farmHelper.ORG_NAME_COL+","+ E_farmHelper.STORAGE_SERVICE_TABLE+"."+ E_farmHelper.ORG_ID_COL_FRKEY+
                ","+ E_farmHelper.ORGANISATION_TABLE+"."+ E_farmHelper.ORG_PRI_ID_COL+","+ E_farmHelper.ORG_NAME_COL
                +","+ E_farmHelper.PRICE_KG_PERISH_COL+","+ E_farmHelper.PRICE_KG_NONPERISH_COL+","+ E_farmHelper.MAX_WEIGHT_COL+","
                + E_farmHelper.MIN_WEIGHT_COL+","+ E_farmHelper.MAX_SPACE_COL+","+ E_farmHelper.MIN_SPACE_COL+","+ E_farmHelper.TP_PRICE_COL+","
                + E_farmHelper.ORG_LOC_COL+","+ E_farmHelper.ORG_CONTACTNO_COL+","+ E_farmHelper.ORG_EMAIL_COL+","+ E_farmHelper.ORG_USERN_COL+
                " FROM "+ E_farmHelper.ORGANISATION_TABLE+" JOIN "+ E_farmHelper.STORAGE_SERVICE_TABLE+ " ON "
                + E_farmHelper.ORGANISATION_TABLE+"."+ E_farmHelper.ORG_ID_COL+" = "+ E_farmHelper.STORAGE_SERVICE_TABLE+"."+ E_farmHelper.ORG_ID_COL_FRKEY+
                ";";

        return db.rawQuery(selOrg_Query,null);

    }


  /*  public Cursor myStorageMethod(String usern)

    {
        SQLiteDatabase db = e_farmHelper.getReadableDatabase();

        String myStorage_Query = "SELECT "+E_farmHelper.ORG_NAME_COL+","+E_farmHelper.PROD_NAME_COL+","+E_farmHelper.PROD_TYPE_COL+","+E_farmHelper.PROD_QTY_COL+","
                +E_farmHelper.STO_DUR_COL+","+E_farmHelper.STO_SPACE_COL+","+E_farmHelper.STO_TP_OPTION_COL+","+
        E_farmHelper.FARMER_STORAGE_TABLE+"."+E_farmHelper.ORG_ID_COL_FKEY+","+
        E_farmHelper.ORGANISATION_TABLE+"."+E_farmHelper.ORG_USERN_COL+","
                +E_farmHelper.ORG_LOC_COL+","+E_farmHelper.ORG_CONTACTNO_COL+","+E_farmHelper.ORG_EMAIL_COL+" FROM "
        +E_farmHelper.FARMER_STORAGE_TABLE+" JOIN (SELCET * FROM "+E_farmHelper.ORGANISATION_TABLE+" WHERE";
    }*/




    //Organisation Storage Service Insert Method
    public long registerStoServiceMethod(String org_id,String perish_price,String nonperish_price,String min_weight,
                                            String max_weight,String min_space,String max_space,String tp_price)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //String org_id ="SELECT ";

        contentValues.put(E_farmHelper.ORG_ID_COL_FRKEY,org_id);
        contentValues.put(E_farmHelper.PRICE_KG_PERISH_COL,perish_price);
        contentValues.put(E_farmHelper.PRICE_KG_NONPERISH_COL,nonperish_price);
        contentValues.put(E_farmHelper.MIN_WEIGHT_COL,min_weight);
        contentValues.put(E_farmHelper.MAX_WEIGHT_COL,max_weight);
        contentValues.put(E_farmHelper.MIN_SPACE_COL,min_space);
        contentValues.put(E_farmHelper.MAX_SPACE_COL,max_space);
        contentValues.put(E_farmHelper.TP_PRICE_COL,tp_price);

        long id = db.insert(E_farmHelper.STORAGE_SERVICE_TABLE,null,contentValues);
        return id;

    }





    //All Organisation Storage Service Query Method
    public Cursor allOrgStoServQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.ORG_ID_COL_FRKEY, E_farmHelper.PRICE_KG_PERISH_COL, E_farmHelper.PRICE_KG_NONPERISH_COL, E_farmHelper.MIN_WEIGHT_COL,
                E_farmHelper.MAX_WEIGHT_COL, E_farmHelper.MIN_SPACE_COL, E_farmHelper.MAX_SPACE_COL, E_farmHelper.TP_PRICE_COL};

        Cursor cursor = db.query(E_farmHelper.STORAGE_SERVICE_TABLE,columns,null,null,null,null,null);

            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }

        }





    //A Particular Organisation Storage Service Query Method
    public Cursor an_OrgStoServQuery(String username)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.PRICE_KG_PERISH_COL, E_farmHelper.PRICE_KG_NONPERISH_COL, E_farmHelper.MIN_WEIGHT_COL,
                E_farmHelper.MAX_WEIGHT_COL, E_farmHelper.MIN_SPACE_COL, E_farmHelper.MAX_SPACE_COL, E_farmHelper.TP_PRICE_COL};

        Cursor cursor = db.query(E_farmHelper.STORAGE_SERVICE_TABLE,columns, E_farmHelper.ORG_USERN_COL+" = '"+username+"'",null,null,null,null);


            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }
        }




    //Diagnosis Insert Method
    public long registerDiseaseMethod(String username,String crop_name,String disease_id,String disease_name,String cure,String prev)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.USER_NAME_DIAG,username);
        contentValues.put(E_farmHelper.CROP_NAME,crop_name);
        contentValues.put(E_farmHelper.DISEASE_ID,disease_id);
        contentValues.put(E_farmHelper.DISEASE_NAME,disease_name);
        contentValues.put(E_farmHelper.DISEASE_CURE,cure);
        contentValues.put(E_farmHelper.DISEASE_PREV,prev);

        long id = db.insert(E_farmHelper.DIAGNOSIS_TABLE,null,contentValues);
        return id;


    }





    //All Diagnosis Info Query Method
    public Cursor allDiagnosisQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.USER_NAME_DIAG, E_farmHelper.CROP_NAME, E_farmHelper.DISEASE_ID, E_farmHelper.DISEASE_NAME,
                E_farmHelper.DISEASE_CURE, E_farmHelper.DISEASE_PREV};

        Cursor cursor = db.query(E_farmHelper.DIAGNOSIS_TABLE,columns,null,null,null,null,null);


            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }
        }






    //A Particular  Diagnosis Info Query Method done by user
    public Cursor a_DiagnosisQuery(String username)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.CROP_NAME, E_farmHelper.DISEASE_ID, E_farmHelper.DISEASE_NAME,
                E_farmHelper.DISEASE_CURE, E_farmHelper.DISEASE_PREV};

        Cursor cursor = db.query(E_farmHelper.DIAGNOSIS_TABLE,columns, E_farmHelper.USER_NAME_DIAG+" = '"+username+"'",null,null,null,null);


/*while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(E_farmHelper.CROP_NAME);
            int index2 = cursor.getColumnIndex(E_farmHelper.DISEASE_ID);
            int index3 = cursor.getColumnIndex(E_farmHelper.DISEASE_NAME);
            int index4 = cursor.getColumnIndex(E_farmHelper.DISEASE_CURE);
            int index5 = cursor.getColumnIndex(E_farmHelper.DISEASE_PREV);


            String crpNm = cursor.getString(index1);
            String desID = cursor.getString(index2);
            String desN = cursor.getString(index3);
            String desCure = cursor.getString(index4);
            String desPrev = cursor.getString(index5);

            //To be continued...
        }*/

            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }
        }



//Diagnosis result for particular user >>select affected_crop,disease_name,disease_cure,disease_prevention from diagnosis_table where des_id  IN
// (Select des_id From sysmptoms where des_id = "gotten from diagnoses done by user" HAVING = ""username"(entered by user)


    //Diagnosis result >>select affected_crop,disease_name,disease_cure,disease_prevention from diagnosis_table where des_id  IN (Select des_id From sysmptoms where des_id = "gotten from diagnoses done by user")




    //Symptoms Insert Method
    public long registerSymptomsMethod(String disease_id,String affected_crop,String shape,String color,String loc,String nature,
                                       String affectedPart,String arrangement)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //String disease_id ="SELECT "+E_farmHelper.DISEASES_ID+" FROM "+E_farmHelper.DIAGNOSES_TABLE+" WHERE ";

        contentValues.put(E_farmHelper.DES_ID_FK,disease_id);
        contentValues.put(E_farmHelper.AFFECTED_CROP,affected_crop);
        contentValues.put(E_farmHelper.DEFECT_SHAPE,shape);
        contentValues.put(E_farmHelper.DEFECT_COLOR,color);
        contentValues.put(E_farmHelper.DEFECT_LOC,loc);
        contentValues.put(E_farmHelper.DEFECT_NATURE,nature);
        contentValues.put(E_farmHelper.DEFECT_PART_AFFECTED,affectedPart);
        contentValues.put(E_farmHelper.DEFECT_ARRG,arrangement);

        long id = db.insert(E_farmHelper.SYMPTOMS_TABLE,null,contentValues);
        return id;

    }




/*

    //All Symptom Info Query Method
    public Cursor allSymptomQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.DES_ID_FK, E_farmHelper.AFFECTED_CROP, E_farmHelper.DEFECT_SHAPE, E_farmHelper.DEFECT_COLOR,
                E_farmHelper.DEFECT_LOC, E_farmHelper.DEFECT_NATURE, E_farmHelper.DEFECT_PART_AFFECTED, E_farmHelper.DEFECT_ARRG};

        Cursor cursor = db.query(E_farmHelper.SYMPTOMS_TABLE,columns,null,null,null,null,null);

       */
/* while (cursor.moveToNext())
        {
            int index1 = cursor.getColumnIndex(E_farmHelper.DES_ID_FK);
            int index2 = cursor.getColumnIndex(E_farmHelper.AFFECTED_CROP);
            int index3 = cursor.getColumnIndex(E_farmHelper.DEFECT_SHAPE);
            int index4 = cursor.getColumnIndex(E_farmHelper.DEFECT_COLOR);
            int index5 = cursor.getColumnIndex(E_farmHelper.DEFECT_LOC);
            int index6 = cursor.getColumnIndex(E_farmHelper.DEFECT_NATURE);
            int index7 = cursor.getColumnIndex(E_farmHelper.DEFECT_PART_AFFECTED);
            int index8 = cursor.getColumnIndex(E_farmHelper.DEFECT_ARRG);

            String desID_FK = cursor.getString(index1);
            String affCrp = cursor.getString(index2);
            String defS = cursor.getString(index3);
            String defC = cursor.getString(index4);
            String defL = cursor.getString(index5);
            String defN = cursor.getString(index6);
            String def_prtaff = cursor.getString(index7);
            String defArrg = cursor.getString(index8);

            //To be continued...
            }*//*

            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }
        }

*/




    //Diagnosis Selection Insert Method
    public long setDiagnosisSelecetionMethod(String affected_crop,String shape,String color,String loc,String nature,
                                       String affectedPart,String arrangement)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //String disease_id ="SELECT "+E_farmHelper.DISEASES_ID+" FROM "+E_farmHelper.DIAGNOSES_TABLE+" WHERE ";

        contentValues.put(E_farmHelper.AFFECTED_CROP_SEL,affected_crop);
        contentValues.put(E_farmHelper.DEFECT_SHAPE_SEL,shape);
        contentValues.put(E_farmHelper.DEFECT_COLOR_SEL,color);
        contentValues.put(E_farmHelper.DEFECT_LOC_SEL,loc);
        contentValues.put(E_farmHelper.DEFECT_NATURE_SEL,nature);
        contentValues.put(E_farmHelper.DEFECT_PART_AFFECTED_SEL,affectedPart);
        contentValues.put(E_farmHelper.DEFECT_ARRG_SEL,arrangement);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }




   //Diagnosis Method
/*
    public String diagnoseMethod(String affcrop,String defS,String dCol,String dLoc,String dAffpart,String dNat,String dArrg)
    {


        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        String diagnosis_Query = "SELECT "+ E_farmHelper.DES_ID_FK+" FROM "+ E_farmHelper.SYMPTOMS_TABLE+" WHERE "+ E_farmHelper.AFFECTED_CROP+
                " = '"+affcrop+"' AND "+ E_farmHelper.DEFECT_SHAPE+" = '"+defS+"' AND "+ E_farmHelper.DEFECT_COLOR+" = '"+dCol+"'"
                +" AND "+ E_farmHelper.DEFECT_LOC+" = '"+dLoc+"' AND "+ E_farmHelper.DEFECT_PART_AFFECTED+" = '"+dAffpart+"' AND "
                + E_farmHelper.DEFECT_NATURE+" = '"+dNat+"' AND "+ E_farmHelper.DEFECT_ARRG+" = '"+dArrg+"';";

        return db.rawQuery(diagnosis_Query,null).toString();

    }*/






    //Diagnosis Report Method
    public  Cursor viewDiagnosisReport(String des_id)
    {

        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        String[] columns = {e_farmHelper.SYMP_ID, e_farmHelper.CROP_NAME, e_farmHelper.DISEASE_NAME, e_farmHelper.DISEASE_CURE,
                e_farmHelper.DISEASE_PREV};

        Cursor cursor = db.query(e_farmHelper.DIAGNOSIS_TABLE,columns, e_farmHelper.DISEASE_ID+" = '"+des_id+"'",null,null,null,null);

        return cursor;

/*
        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }
*/

    }








    //Diagnosis Alternative Method


    public Cursor diagnoseMethodAlt(String affcrop,String defS,String dCol,String dLoc,String dAffpart,String dNat,String dArrg)
    {


        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        String diagnosis_Query = "SELECT "+ E_farmHelper.DES_ID_FK+" FROM "+ E_farmHelper.SYMPTOMS_TABLE+" WHERE "+ E_farmHelper.AFFECTED_CROP+
                " = '"+affcrop+"' AND "+ E_farmHelper.DEFECT_SHAPE+" = '"+defS+"' AND "+ E_farmHelper.DEFECT_COLOR+" = '"+dCol+"'"
                +" AND "+ E_farmHelper.DEFECT_LOC+" = '"+dLoc+"' AND "+ E_farmHelper.DEFECT_PART_AFFECTED+" = '"+dAffpart+"' AND "
                + E_farmHelper.DEFECT_NATURE+" = '"+dNat+"' AND "+ E_farmHelper.DEFECT_ARRG+" = '"+dArrg+"';";

        Cursor desID  = db.rawQuery(diagnosis_Query,null);
      //  desID.moveToFirst();
        return desID;

    }







    //Diagnosis Affected Crop Insert Method
    public long setAffectedCropMethod(String affected_crop)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.AFFECTED_CROP_SEL,affected_crop);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }



    //Diagnosis Defect Shape Insert Method
    public long setDefectShapeMethod(String shape)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(E_farmHelper.DEFECT_SHAPE_SEL,shape);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }



    //Diagnosis Defect Color Insert Method
    public long setDiagnosisDefectColorMethod(String color)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.DEFECT_COLOR_SEL,color);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }


    //Diagnosis Defect Location Insert Method
    public long setDiagnosisDefeLocationMethod(String loc)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.DEFECT_LOC_SEL,loc);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }



    //Diagnosis Defect Nature Insert Method
    public long setDiagnosisDefNatureMethod(String nature)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.DEFECT_NATURE_SEL,nature);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }


    //Diagnosis Defect Affected Part Insert Method
    public long setDiagnosisDefAffPartMethod(String affectedPart)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.DEFECT_PART_AFFECTED_SEL,affectedPart);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }



    //Diagnosis Defect Arrangement Insert Method
    public long setDiagnosisDefArggMethod(String arrangement)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(E_farmHelper.DEFECT_ARRG_SEL,arrangement);

        long id = db.insert(E_farmHelper.SET_DIAGNOSIS_TABLE,null,contentValues);
        return id;

    }


    //Upload Product Insert Method
    public long uploadProductMethod(String usern,String photo,String name,String type,String qty,String price,String decrp)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //String usern = "SELECT";
        String status = "Available";

        contentValues.put(E_farmHelper.USER_NAME,usern);
        contentValues.put(E_farmHelper.PROD_PHOTO,photo);
        contentValues.put(E_farmHelper.PROD_NAME,name);
        contentValues.put(E_farmHelper.PROD_TYPE,type);
        contentValues.put(E_farmHelper.PROD_QTY,qty);
        contentValues.put(E_farmHelper.PROD_PRICE,price);
        contentValues.put(E_farmHelper.PROD_DSCRP,decrp);
        contentValues.put(E_farmHelper.PROD_STATUS,status);


        long id = db.insert(E_farmHelper.PRODUCT_TABLE,null,contentValues);
        return id;
    }



    //All Product Query Method
    public Cursor allProdQuery()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.FARMER_ID_COL, E_farmHelper.PROD_NAME, E_farmHelper.PROD_TYPE,
                E_farmHelper.PROD_QTY, E_farmHelper.PROD_PRICE, E_farmHelper.PROD_DSCRP,
                E_farmHelper.USER_NAME, E_farmHelper.PROD_PHOTO, E_farmHelper.PROD_STATUS};

        Cursor cursor = db.query(E_farmHelper.PRODUCT_TABLE,columns,null,null,null,null,null);

            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }

        }





    //A PArticular Product Query Method for user
    public Cursor a_ProdQuery(String username)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.FARMER_ID_COL, E_farmHelper.PROD_NAME, E_farmHelper.PROD_TYPE,
                E_farmHelper.PROD_QTY, E_farmHelper.PROD_PRICE, E_farmHelper.PROD_DSCRP,
                E_farmHelper.USER_NAME, E_farmHelper.PROD_PHOTO, E_farmHelper.PROD_STATUS};

        Cursor cursor = db.query(E_farmHelper.PRODUCT_TABLE,columns, E_farmHelper.USER_NAME+" = '"+username+"'",null,null,null,null);

            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }

        }





    public String DeleteProductData()
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        try{
            String DELETE_QUERY= "DELETE FROM "+E_farmHelper.PRODUCT_TABLE;
            db.execSQL(DELETE_QUERY);

            return "Data Deleted Successfully";
        } catch (Exception ex)
        {
            return "Error!! "+ ex.getMessage();
        }
    }



    public Cursor contactSellerMethod(String usern)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.PROD_ID};

        Cursor cursor = db.query(E_farmHelper.PRODUCT_TABLE,columns, E_farmHelper.PROD_ID+" = '"+id+"'",null,null,null,null);


        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }

    }





    public Cursor get_ProdID_Query(long id)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.PROD_ID};

        Cursor cursor = db.query(E_farmHelper.PRODUCT_TABLE,columns, E_farmHelper.PROD_ID+" = '"+id+"'",null,null,null,null);


        if(cursor != null)
        {
            cursor.moveToFirst();
            return cursor;
        }

        else
        {
            return null;
        }

    }


    //Contact Us Insert Method
    public long contactUsMethod(String fname,String email,String cont,String msg)
    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put(E_farmHelper.USER_N,userN);
        contentValues.put(E_farmHelper.FNAME,fname);
        contentValues.put(E_farmHelper.USER_EMAIL,email);
        contentValues.put(E_farmHelper.USER_CONT,cont);
        contentValues.put(E_farmHelper.MESSAGE,msg);

        long id = db.insert(E_farmHelper.CONTACT_US_TABLE,null,contentValues);
        return id;

    }


    //All Contact Info Query Method
    public Cursor allContactQuery()

    {
        SQLiteDatabase db = e_farmHelper.getWritableDatabase();

        String[] columns = {E_farmHelper.FNAME, E_farmHelper.USER_EMAIL, E_farmHelper.USER_CONT, E_farmHelper.MESSAGE};

        Cursor cursor = db.query(E_farmHelper.DIAGNOSIS_TABLE,columns,null,null,null,null,null);


            if(cursor != null)
            {
                cursor.moveToFirst();
                return cursor;
            }

            else
            {
                return null;
            }
        }



    public Cursor farmerLogin()
    {
        String query = "SELECT username,password,role FROM farmer ";
        return null;
    }






  /* TABLES, COLUMNS, DB CREATION, DB UPDATE code section*/
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

     static class E_farmHelper extends SQLiteOpenHelper {

        private Context context;

        /* Database */
        private static final String DB_NAME = "e_farmdb";

        /*Tables*/
        private static String ADMIN_TABLE = "admin";
        private static String FARMER_TABLE = "farmer";
        private static String ORGANISATION_TABLE = "organisation";
        private static String FARMER_STORAGE_TABLE = "farmer_storage";
        private static String STORAGE_SERVICE_TABLE = "storage_service";
        private static String DIAGNOSIS_TABLE = "diagnosis";
        private static String SYMPTOMS_TABLE = "symptom";
        private static String PRODUCT_TABLE = "product";
        private static String CONTACT_US_TABLE = "contact_us";
         private static String SET_DIAGNOSIS_TABLE = "diagnosis_selection";



        /*Admin table columns*/
        private static String ADMIN_ID_COL = "_id";
        private static String ADMIN_FNAME_COL = "first_name";
        private static String ADMIN_LNAME_COL = "last_name";
        private static String ADMIN_LOC_COL = "location";
        private static String ADMIN_CONTACTNO_COL = "contact_no";
        private static String ADMIN_EMAIL_COL = "email";
        private static String ADMIN_USERN_COL = "username";
        private static String ADMIN_PASS_COL = "password";
        private static String ADMIN_PHOTO_COL = "photo";
         private static String ADMIN_ROLE_COL = "role";


        /*Create table command for Admin */
        private static String CREATE_ADMIN_TABLE = "CREATE TABLE "+ADMIN_TABLE+" ("+ADMIN_ID_COL+" INT AUTO_INCREMENT,"
                +ADMIN_FNAME_COL+" TEXT,"+ADMIN_LNAME_COL+" TEXT,"+ADMIN_LOC_COL+" TEXT,"+ADMIN_CONTACTNO_COL+" TEXT,"+ADMIN_EMAIL_COL+" TEXT,"
                +ADMIN_USERN_COL+" VARCHAR(255) PRIMARY KEY NOT NULL,"+ADMIN_PASS_COL+" TEXT,"+ADMIN_PHOTO_COL+" TEXT,"+ADMIN_ROLE_COL+" TEXT);";


        private static String SELECT_ADMIN_TABLE = "SELECT "+ADMIN_PHOTO_COL+","+ADMIN_USERN_COL+","+ADMIN_FNAME_COL+","+ADMIN_LNAME_COL+","+ADMIN_LOC_COL+","+
                ADMIN_CONTACTNO_COL+","+ADMIN_EMAIL_COL+", FROM "+ADMIN_TABLE+";";

        private static String DROP_ADMIN_TABLE = "DROP TABLE IF EXISTS "+ADMIN_TABLE+";";



        /*Farmer table columns*/
        private static String FARMER_ID_COL = "_id";
        private static String FARMER_FNAME_COL = "first_name";
        private static String FARMER_LNAME_COL = "last_name";
        private static String FARMER_LOC_COL = "location";
        private static String FARMER_CONTACTNO_COL = "contact_no";
        private static String FARMER_EMAIL_COL = "email";
        private static String FARMER_USERN_COL = "username";
        private static String FARMER_PASS_COL = "password";
        private static String FARMER_PHOTO_COL = "photo";
         private static String FARMER_ROLE_COL = "role";


        /*Create table command for Farmer */
        private static String CREATE_FARMER_TABLE = "CREATE TABLE "+FARMER_TABLE+" ("+FARMER_ID_COL+" INT AUTO_INCREMENT,"
                +FARMER_FNAME_COL+" TEXT,"+FARMER_LNAME_COL+" TEXT,"+FARMER_LOC_COL+" TEXT,"+FARMER_CONTACTNO_COL+" TEXT,"+FARMER_EMAIL_COL+" TEXT,"
                +FARMER_USERN_COL+" VARCHAR(255) PRIMARY KEY NOT NULL,"+FARMER_PASS_COL+" TEXT,"+FARMER_PHOTO_COL+" TEXT,"+FARMER_ROLE_COL+" TEXT);";


        private static String SELECT_FARMER_TABLE = "SELECT "+FARMER_PHOTO_COL+","+FARMER_USERN_COL+","+FARMER_FNAME_COL+","+FARMER_LNAME_COL+","+FARMER_LOC_COL+","+
                FARMER_CONTACTNO_COL+","+FARMER_EMAIL_COL+"FROM "+FARMER_TABLE+";";


        private static String DROP_FARMER_TABLE = "DROP TABLE IF EXISTS "+FARMER_TABLE+";";


        /*Organisationa table columns*/
        private static String ORG_PRI_ID_COL = "_id";
         private static String ORG_ID_COL = "organisation_id";
         private static String ORG_NAME_COL = "organisation_name";
        private static String ORG_OPLIC_ID_COL = "operational_id";
        private static String ORG_YROFEST_COL = "year_of_est";
        private static String ORG_LOC_COL = "location";
        private static String ORG_CONTACTNO_COL = "contact_no";
        private static String ORG_EMAIL_COL = "email";
        private static String ORG_USERN_COL = "username";
        private static String ORG_PASS_COL = "password";
        private static String ORG_PHOTO_COL = "photo";
         private static String ORG_ROLE_COL = "role";

        private static String CREATE_ORG_TABLE = "CREATE TABLE "+ORGANISATION_TABLE+" ("+ORG_PRI_ID_COL+" INT AUTO_INCREMENT,"+ORG_ID_COL+" TEXT PRIMARY KEY NOT NULL,"
                +ORG_NAME_COL+" TEXT,"+ORG_OPLIC_ID_COL+" TEXT,"+ORG_YROFEST_COL+" TEXT,"+ORG_LOC_COL+" TEXT,"
                +ORG_CONTACTNO_COL+" TEXT,"+ORG_EMAIL_COL+" TEXT,"+ORG_USERN_COL+" TEXT,"+ORG_PASS_COL+" TEXT,"+ORG_PHOTO_COL+" TEXT,"+ORG_ROLE_COL+" TEXT);";

        private static String SELECT_ORG_TABLE = "SELECT "+ORG_PHOTO_COL+","+ORG_NAME_COL+","+ORG_ID_COL+","+ORG_OPLIC_ID_COL+
                ","+ORG_YROFEST_COL+","+ORG_LOC_COL+","+ORG_CONTACTNO_COL+","+ORG_EMAIL_COL+","+ORG_USERN_COL+" FROM "+ORGANISATION_TABLE+";";


        private static String DROP_ORG_TABLE = "DROP TABLE IF EXISTS "+ORGANISATION_TABLE+";";



        /*Farmer Storage Service table columns*/
        private static String STO_ID_COL = "_id";
        private static String ORG_ID_COL_FKEY = "organisation_id";
        private static String PROD_NAME_COL = "product_name";
        private static String PROD_TYPE_COL = "product_type";
        private static String PROD_QTY_COL = "product_qty";
        private static String STO_DUR_COL = "storage_duration";
        private static String STO_SPACE_COL = "storage_space";
        private static String STO_TP_OPTION_COL = "transport_opt";
        private static String FRM_REGISTERED_COL = "username"; //Contains customer username

        private static String CREATE_FARMER_STORAGE_TABLE = "CREATE TABLE "+FARMER_STORAGE_TABLE+" ("+STO_ID_COL+" INT AUTO_INCREMENT,"
                +ORG_ID_COL_FKEY+" VARCHAR(255) NOT NULL,"+PROD_NAME_COL+" TEXT,"+PROD_TYPE_COL+" TEXT,"+PROD_QTY_COL+" TEXT,"+STO_DUR_COL+" TEXT,"
                +STO_SPACE_COL+" TEXT,"+STO_TP_OPTION_COL+" TEXT,"+FRM_REGISTERED_COL+" VARCHAR(255) NOT NULL,PRIMARY KEY ("+STO_ID_COL+")," +
                "FOREIGN KEY ("+ORG_ID_COL_FKEY+") REFERENCES "+ORGANISATION_TABLE+"("+ORG_ID_COL_FKEY+"),FOREIGN KEY ("+FRM_REGISTERED_COL+")" +
                " REFERENCES "+FARMER_TABLE+"("+FARMER_USERN_COL+"));";


        private static String SELECT_FARMER_STORAGE_TABLE = "SELECT "+ORG_ID_COL_FKEY+","+PROD_NAME_COL+","+PROD_TYPE_COL+","+PROD_QTY_COL+
                ","+STO_DUR_COL+","+STO_SPACE_COL+","+STO_TP_OPTION_COL+" FROM "+FARMER_STORAGE_TABLE+";";


        private static String DROP_FARMER_STORAGE_TABLE = "DROP TABLE IF EXISTS "+FARMER_STORAGE_TABLE+";";


        /*Organisation Storage Provider table columns*/
        private static String STO_SERV_ID_COL = "_id";
        private static String ORG_ID_COL_FRKEY = "organisation_id";
        private static String PRICE_KG_PERISH_COL = "price_per_kg_perishable";
        private static String PRICE_KG_NONPERISH_COL = "price_per_kg_nonperishable";
        private static String MIN_WEIGHT_COL = "min_weight_accepted";
        private static String MAX_WEIGHT_COL = "max_weight_accepted";
        private static String TP_PRICE_COL = "tp_price";
        private static String MIN_SPACE_COL = "min_space_available";
        private static String MAX_SPACE_COL = "max_space_available";



        private static String CREATE_STO_PROV_TABLE = "CREATE TABLE "+STORAGE_SERVICE_TABLE+" ("+STO_SERV_ID_COL+" INT AUTO_INCREMENT,"
                +ORG_ID_COL_FRKEY+" VARCHAR(255) NOT NULL,"+PRICE_KG_PERISH_COL+" TEXT,"+PRICE_KG_NONPERISH_COL+" TEXT,"+MIN_WEIGHT_COL+" TEXT,"+MAX_WEIGHT_COL+" TEXT,"
                +TP_PRICE_COL+" TEXT,"+MIN_SPACE_COL+" TEXT,"+MAX_SPACE_COL+" TEXT,PRIMARY KEY ("+STO_SERV_ID_COL+"),FOREIGN KEY ("+ORG_ID_COL_FRKEY+
                ") REFERENCES "+ORGANISATION_TABLE+"("+ORG_ID_COL+"));";


        private static String SELECT_STO_PROV_TABLE = "SELECT "+PRICE_KG_PERISH_COL+","+PRICE_KG_NONPERISH_COL+","+MIN_WEIGHT_COL+","+MAX_WEIGHT_COL+
                ","+MIN_SPACE_COL+","+MAX_SPACE_COL+","+TP_PRICE_COL+" FROM "+STORAGE_SERVICE_TABLE+";";


        private static String DROP_STORAGE_SERVICE_TABLE = "DROP TABLE IF EXISTS "+STORAGE_SERVICE_TABLE+";";

        /*Diagnosis table columns*/
        private static String USER_NAME_DIAG = "username";
        private static String CROP_NAME = "crop_name";
        private static String DIAGNOSIS_ID = "_id";
        private static String DISEASE_ID = "disease_id";
        private static String DISEASE_NAME = "disease_name";
         private static String DISEASE_CURE = "disease_cure";
        private static String DISEASE_PREV = "disease_prevention";




        private static String CREATE_DIAGNOSES_TABLE = "CREATE TABLE "+DIAGNOSIS_TABLE+" ("+DIAGNOSIS_ID+" INT AUTO_INCREMENT,"
                +CROP_NAME+" TEXT,"+DISEASE_ID+" VARCHAR(255) PRIMARY KEY NOT NULL,"+DISEASE_NAME+" TEXT,"+DISEASE_CURE+" TEXT,"+DISEASE_PREV+" TEXT,"+USER_NAME_DIAG+" VARCHAR(255) NOT NULL," +
                "FOREIGN KEY ("+USER_NAME_DIAG+") REFERENCES "+FARMER_TABLE+"("+FARMER_USERN_COL+"));";

        private static String SELECT_DIANOSES_TABLE = "SELECT "+DISEASE_ID+","+DISEASE_NAME+","+DISEASE_CURE+","+DISEASE_PREV+" FROM "+DIAGNOSIS_TABLE+";";


        private static String DROP_DIAGNOSES_TABLE = "DROP TABLE IF EXISTS "+DIAGNOSIS_TABLE+";";



        /* Symptom table columns*/
        private static String SYMP_ID = "_id";
        private static String DES_ID_FK = "disease_id";
        //private static String DES_NAME = "disease_name";
        private static String AFFECTED_CROP = "affected_crop";
        private static String DEFECT_SHAPE = "defect_shape";
        private static String DEFECT_COLOR = "defect_color";
        private static String DEFECT_LOC = "defect_location";//LEAF,STEM,ROOT
        private static String DEFECT_NATURE = "defect_nature";
        private static String DEFECT_PART_AFFECTED = "affected_part_of_plant";
        private static String DEFECT_ARRG = "defect_arrangement";


        private static String CREATE_SYMPTOM_TABLE = "CREATE TABLE "+SYMPTOMS_TABLE+" ("+SYMP_ID+" INT AUTO_INCREMENT,"
                +DES_ID_FK+" VARCHAR(255) NOT NULL,"+AFFECTED_CROP+" TEXT,"+DEFECT_SHAPE+" TEXT,"+DEFECT_COLOR+" TEXT,"+DEFECT_LOC+" TEXT,"
                +DEFECT_NATURE+" TEXT,"+DEFECT_PART_AFFECTED+" TEXT,"+DEFECT_ARRG+" TEXT,PRIMARY KEY ("+SYMP_ID+")," +
                "FOREIGN KEY ("+DES_ID_FK+") REFERENCES "+DIAGNOSIS_TABLE+"("+DISEASE_ID+"));";


        private static String SELECT_SYMPTOM_TABLE = "SELECT "+DES_ID_FK+","+AFFECTED_CROP+","+DEFECT_SHAPE+","+DEFECT_COLOR+
                ","+DEFECT_LOC+","+DEFECT_PART_AFFECTED+","+DEFECT_NATURE+","+DEFECT_ARRG+" FROM "+SYMPTOMS_TABLE+";";


        private static String DROP_SYMPTOMS_TABLE = "DROP TABLE IF EXISTS "+SYMPTOMS_TABLE+";";





         /* Set Diagnosis Selection table columns*/
         private static String SYMP_SELECTION_ID = "_id";
         private static String AFFECTED_CROP_SEL = "affected_crop";
         private static String DEFECT_SHAPE_SEL = "defect_shape";
         private static String DEFECT_COLOR_SEL = "defect_color";
         private static String DEFECT_LOC_SEL = "defect_location";//LEAF,STEM,ROOT
         private static String DEFECT_NATURE_SEL = "defect_nature";
         private static String DEFECT_PART_AFFECTED_SEL = "affected_part_of_plant";
         private static String DEFECT_ARRG_SEL = "defect_arrangement";


         private static String CREATE_DIAGNOSIS_SEL_TABLE = "CREATE TABLE "+SET_DIAGNOSIS_TABLE+" ("+SYMP_SELECTION_ID+" INTEGER AUTO_INCREMENT PRIMARY KEY,"
                 +AFFECTED_CROP_SEL+" TEXT,"+DEFECT_SHAPE_SEL+" TEXT,"+DEFECT_COLOR_SEL+" TEXT,"+DEFECT_LOC_SEL+" TEXT,"
                 +DEFECT_NATURE_SEL+" TEXT,"+DEFECT_PART_AFFECTED_SEL+" TEXT,"+DEFECT_ARRG_SEL+" TEXT);";


       private static String SELECET_DIAGNOSIS_SEL_TABLE = "SELECT "+AFFECTED_CROP_SEL+","+DEFECT_SHAPE_SEL+","+DEFECT_COLOR_SEL+
                 ","+DEFECT_LOC_SEL+","+DEFECT_PART_AFFECTED_SEL+","+DEFECT_NATURE_SEL+","+DEFECT_ARRG_SEL+" FROM "+SET_DIAGNOSIS_TABLE+";";


         private static String DROP_DIAGNOSIS_SEL_TABLE = "DROP TABLE IF EXISTS "+SET_DIAGNOSIS_TABLE+";";



        /* Product table columns*/
        private static String PROD_ID = "_id";
        private static String PROD_NAME = "product_name";
        private static String PROD_TYPE = "product_type";
        private static String PROD_QTY = "product_quantity";
        private static String PROD_PRICE = "product_price";
        private static String PROD_DSCRP = "product_description";
        private static String PROD_PHOTO = "product_photo";
         private static String PROD_STATUS = "status";
        private static String USER_NAME = "username";



        private static String CREATE_PROD_TABLE = "CREATE TABLE "+PRODUCT_TABLE+" ("+PROD_ID+" INT AUTO_INCREMENT,"
                +PROD_NAME+" TEXT,"+PROD_TYPE+" TEXT,"+PROD_QTY+" TEXT,"+PROD_PRICE+" TEXT,"+PROD_DSCRP+" TEXT,"
                +PROD_PHOTO+" TEXT,"+PROD_STATUS+" TEXT,"+USER_NAME+" VARCHAR(255) NOT NULL,PRIMARY KEY ("+PROD_ID+"),FOREIGN KEY ("+USER_NAME+") "+
                "REFERENCES "+FARMER_TABLE+"("+FARMER_USERN_COL+"));";


        private static String SELECT_PROD_TABLE = "SELECT "+PROD_PHOTO+","+PROD_NAME+","+PROD_TYPE+","+PROD_QTY+","+PROD_PRICE+
                ","+PROD_DSCRP+" FROM "+PRODUCT_TABLE+";";


        private static String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS "+PRODUCT_TABLE+";";



        /* ContactUs table columns*/
        private static String CONT_ID = "_id";
         private static String USER_EMAIL = "user_email";
        private static String FNAME = "name_of_user";
         private static String USER_CONT = "user_contact";
        private static String MESSAGE = "message";


        private static String CREATE_ContactUs_TABLE = "CREATE TABLE "+CONTACT_US_TABLE+" ("+CONT_ID+" INT AUTO_INCREMENT PRIMARY KEY,"
                +USER_CONT+" TEXT,"+USER_EMAIL+" TEXT,"+FNAME+" TEXT,"+MESSAGE+" TEXT);";


        private static String SELECT_ContactUs_TABLE = "SELECT "+","+USER_EMAIL+","+FNAME+","+MESSAGE+
                " FROM "+CONTACT_US_TABLE+";";


        private static String DROP_ContactUs_TABLE = "DROP TABLE IF EXISTS "+CONTACT_US_TABLE+";";








        /**********Database version*********/

        private static int DB_VERSION = 1; //PREVIOUS 22 CHANGED 11:25 PM {06-04-2017}

        public E_farmHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.context =context;

            Message.message(context,"Constructor called");
        }



        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_ADMIN_TABLE);
                db.execSQL(CREATE_FARMER_TABLE);
                db.execSQL(CREATE_ORG_TABLE);
                db.execSQL(CREATE_FARMER_STORAGE_TABLE);
                db.execSQL(CREATE_STO_PROV_TABLE);
                db.execSQL(CREATE_DIAGNOSES_TABLE);
                db.execSQL(CREATE_SYMPTOM_TABLE);
                db.execSQL(CREATE_PROD_TABLE);
                db.execSQL(CREATE_ContactUs_TABLE);
                db.execSQL(CREATE_DIAGNOSIS_SEL_TABLE);

                Message.message(context,"onCreate called");

            } catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                Message.message(context,"onUpgade called");

                db.execSQL(DROP_ADMIN_TABLE);
                db.execSQL(DROP_FARMER_TABLE);
                db.execSQL(DROP_ORG_TABLE);
                db.execSQL(DROP_FARMER_STORAGE_TABLE);
                db.execSQL(DROP_STORAGE_SERVICE_TABLE);
                db.execSQL(DROP_DIAGNOSES_TABLE);
                db.execSQL(DROP_SYMPTOMS_TABLE);
                db.execSQL(DROP_PRODUCT_TABLE);
                db.execSQL(DROP_ContactUs_TABLE);
                db.execSQL(DROP_DIAGNOSIS_SEL_TABLE);
                onCreate(db);

            }
            catch (SQLException e) {
                Message.message(context, "" + e);
            }
        }

    }
}
