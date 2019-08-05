package vn.edu.poly.sqlonclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<Student> students;
    private StudentReaderSql studentReaderSql;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
        this.studentReaderSql = new StudentReaderSql(context);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        TextView tvname = convertView.findViewById(R.id.tvname);
        TextView tvprice = convertView.findViewById(R.id.tvprice);
        TextView tvid = convertView.findViewById(R.id.tvid);


        final Student student = (Student) getItem(position);
        tvname.setText(student.name);

        tvid.setText(student.id);
        tvprice.setText(student.price);

        convertView.findViewById(R.id.btnX).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentReaderSql.deleteSudent(student.id);
                students.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
