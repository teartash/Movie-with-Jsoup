package com.raj.jsoup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetialActivity extends AppCompatActivity {
    ImageView img;
    TextView txt_detial,txt_language,title_image;
    String detialString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial);

        img=findViewById(R.id.img);
        txt_detial=findViewById(R.id.txt_detial);
        txt_language=findViewById(R.id.txt_language);
        title_image=findViewById(R.id.title_image);


        Picasso.get().load(getIntent().getStringExtra("imageurl")).into(img);
        title_image.setText(getIntent().getStringExtra("title"));
        txt_language.setText(getIntent().getStringExtra("language"));

        Content content=new Content();
        content.execute();




    }
    private class Content extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }

        @Override
        protected Void doInBackground(Void... voids){
            try {
                String baseurl="https://www.cinemaqatar.com/";
                String detial=getIntent().getStringExtra("detialurl");
                String url=baseurl+detial;
                Document doc=Jsoup.connect(url).get();
                Elements data=doc.select("div.detailinfo");
                detialString=data.select("div.detailinfo")
                       .text();
                Log.d("detial", "doInBackground: "+detialString);




            }catch (IOException e){
                e.printStackTrace();


            }


            return null;
        }

        public Content() {
            super();
        }



        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            txt_detial.setText(detialString);

            Log.d("detial", "doInBackground: "+detialString);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}