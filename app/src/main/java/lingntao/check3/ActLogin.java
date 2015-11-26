package lingntao.check3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActLogin extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.ling.tao.MESSAGE";
    Context context;
    EditText et_user, et_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        context = getApplicationContext();
        et_user=(EditText) findViewById(R.id.et_user);
        et_pw=(EditText) findViewById(R.id.et_pw);
    }

    public void login(View view){
        if( et_user.getText().toString().equals("宗佑")&& et_pw.getText().toString().equals("123")){
            Intent intent = new Intent();
            intent.setClass(this, ActMain.class);
            String hello = "使用者：" + et_user.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, hello);
            startActivity(intent);
//            Login.this.finish();
        } else{
            et_user.setText("");
            et_pw.setText("");
            Toast.makeText(context, "帳號密碼錯誤！", Toast.LENGTH_LONG).show();
        }

    }
}
