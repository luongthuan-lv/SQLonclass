package vn.edu.poly.sqlonclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StudentReaderSql extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Student";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PRICE = "price";


    public StudentReaderSql(Context context) {
        super(context, "students.db", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // câu lệnh tạo bảng
        String create_table = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " TEXT PRIMARY KEY, " + COL_NAME + " TEXT,"+COL_PRICE+" TEXT)";

        Log.e("CL", create_table);

        // thuc thi
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // nâng cấp bảng
    }

    public long insertStudent(Student student) {
        // xin quyen
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, student.id);
        contentValues.put(COL_NAME, student.name);
        contentValues.put(COL_PRICE, student.price);

        // cau lenh themvao co so du lieu
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return result;
    }

    public List<Student> getAllStudents() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        List<Student> studentList = new ArrayList<>();
        String truyVan = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(truyVan, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                Student student = new Student();
                student.id = cursor.getString(cursor.getColumnIndex(COL_ID));
                student.name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                student.price = cursor.getString(cursor.getColumnIndex(COL_PRICE));

                studentList.add(student);


                cursor.moveToNext();
            }
            cursor.close();

        }

        return studentList;
    }

    public void deleteSudent(String id) {
        // xin quyen
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COL_ID + "=?", new String[]{id});

    }

    public long updateStudent(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, student.id);
        contentValues.put(COL_NAME, student.name);

        //cau lenh them vao cs du lieu
        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, COL_ID + "=?", new String[]{student.id});

        return result;
    }

}
