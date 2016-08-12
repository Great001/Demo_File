package com.example.dw.demofile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=(TextView)findViewById(R.id.tv_result);

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/*");
        startActivityForResult(intent,1);

        /*
        ImageView img=(ImageView)findViewById(R.id.iv_test);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.up);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
        String str= Base64.encodeToString(bos.toByteArray(),Base64.DEFAULT);
        result.setText(str);
            //ByteArrayInputStream is=new ByteArrayInputStream(Base64.decode(str,Base64.DEFAULT));
        byte[] bmp=Base64.decode(str,Base64.DEFAULT);
            Bitmap bitmap1=BitmapFactory.decodeByteArray(bmp,0,bmp.length);
            if(bitmap1==null){
                Toast.makeText(this,"空",Toast.LENGTH_LONG).show();
            }
            img.setImageBitmap(bitmap1);*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String root=Environment.getExternalStorageDirectory().getAbsolutePath();
        File file=new File(root+File.separator+"good.docx");
        String str= URLDecoder.decode(data.getData().toString());
        String filepath=str.substring(7,str.length());
        result.setText(str);
        try{
            FileInputStream is=new FileInputStream(filepath);
            FileOutputStream fo=new FileOutputStream(file);
            byte [] source=new byte[1024];
           while(is.read(source,0,1024)!=-1){
               fo.write(source);
           }


        }catch(IOException e){}

    }
}
