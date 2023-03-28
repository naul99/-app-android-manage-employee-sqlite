package vn.edu.stu.dangminhluan_dh51900957;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private EditText edtEmail,edtPass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }
    private void addControls() {
        edtEmail=findViewById(R.id.edtEmail);
        edtPass=findViewById(R.id.edtPass);
        btnLogin=findViewById(R.id.btnLogin);

    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLy();
            }
        });

    }
    private void xuLy() {
        String email=edtEmail.getText().toString();
        String password=edtPass.getText().toString();
//        if (email.isEmpty()||password.isEmpty()){
//            Toast.makeText(this, "Thong tin rong !", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        String urlApi="http://document.fitstu.net/2022/ws2.php?action=login&email="+email+"&pass="+password;
//
//        OkHttpClient client=new OkHttpClient().newBuilder().build();
//        okhttp3.Request request=new okhttp3.Request.Builder()
//                .url(urlApi).method("GET",null).build();
//        try {
//            okhttp3.Response response=client.newCall(request).execute();
//            if (response.code()!=200){
//                Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show();
//                return;
//            }
//            String stringData=response.body().string();
//            JSONObject object=new JSONObject(stringData);
//            boolean result=object.getBoolean("KETQUA");
//            if (!result){
//                Toast.makeText(this, "Đăng nhập thất bại !", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            Toast.makeText(this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(MainActivity.this, SinhVienActivity.class);
//            startActivity(intent);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
        RequestQueue queue = Volley.newRequestQueue(this);
        Response.Listener<String> listener = response -> {
            try {
                boolean result = new JSONObject(response).getBoolean("KETQUA");
                if (!result) {
                    Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, SinhVienActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Response.ErrorListener errorListener = error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();

        Uri.Builder builder = Uri.parse("http://document.fitstu.net/2022/ws2.php").buildUpon();

        builder.appendQueryParameter("action", "login");
        builder.appendQueryParameter("email", email);
        builder.appendQueryParameter("pass", password);
        String url = builder.build().toString();
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                listener,
                errorListener
        );

        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        queue.add(request);
    }
}