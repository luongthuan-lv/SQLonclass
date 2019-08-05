package vn.edu.poly.sqlonclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private StudentReaderSql studentReaderSql;

    private ListView lvList;

    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvList=findViewById(R.id.lvList);
        studentReaderSql =new StudentReaderSql(MainActivity.this);
        List<Student> studentList=studentReaderSql.getAllStudents();
        studentAdapter=new StudentAdapter(this,studentList);
        lvList.setAdapter(studentAdapter);
        Log.e("SIZE",studentList.size()+"");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        studentReaderSql.close();
    }
}
