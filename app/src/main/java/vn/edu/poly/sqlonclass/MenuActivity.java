package vn.edu.poly.sqlonclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {


    private StudentReaderSql studentReaderSql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void them(View view) {
        studentReaderSql =new StudentReaderSql(MenuActivity.this);

        Student student=new Student();
        student.id="PH "+System.currentTimeMillis();
        student.name="Hùng Việt "+System.currentTimeMillis();
        student.price="Lương"+System.currentTimeMillis();

        long result=studentReaderSql.insertStudent(student);
        if (result>0){
            Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Lỗi khi thêm",Toast.LENGTH_SHORT).show();
        }

    }
    public void sua(View view) {
    }

    public void xoa(View view) {

    }

    public void danhsach(View view) {
        Intent intent = new Intent(MenuActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
