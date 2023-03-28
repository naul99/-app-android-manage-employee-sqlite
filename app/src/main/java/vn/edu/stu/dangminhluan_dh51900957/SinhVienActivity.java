package vn.edu.stu.dangminhluan_dh51900957;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import vn.edu.stu.dangminhluan_dh51900957.model.Sinhvien;

public class SinhVienActivity extends AppCompatActivity {
    private EditText edtLop;
    private Button btnLop;
    ArrayList<Sinhvien> dsSv;
    ArrayAdapter<Sinhvien> adapter;
    ListView lvSv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);
        addControls();
        addEvents();
    }
    private void addControls() {
        edtLop=findViewById(R.id.edtLop);

        btnLop=findViewById(R.id.btnLop);
        lvSv = findViewById(R.id.lvSv);
        dsSv = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                SinhVienActivity.this, android.R.layout.simple_list_item_1, dsSv
        );
        lvSv.setAdapter(adapter);

    }

    private void addEvents() {
        btnLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hienthiDanhsach();
            }
        });

    }
    private void xuLy() {
        String email=edtLop.getText().toString();
    }
    private void hienthiDanhsach() {
        String lop=edtLop.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(SinhVienActivity.this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    dsSv.clear();
                    JSONArray jsonArray = new JSONArray(response);
                    int len = jsonArray.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String ten = jsonObject.getString("TEN");
                        String diem = jsonObject.getString("DTB");
                        dsSv.add(new Sinhvien(ten, diem));
                    }
                    adapter.notifyDataSetChanged();
                } catch (Exception ignored) {

                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SinhVienActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        Uri.Builder builder = Uri.parse("http://document.fitstu.net/2022/ws2.php").buildUpon();
        builder.appendQueryParameter("action", "getsinhvien");
        builder.appendQueryParameter("lop", lop);
        String url = builder.build().toString();
        StringRequest request = new StringRequest(
                Request.Method.GET, url, responseListener, errorListener
        );
        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );
        requestQueue.add(request);
    }
}