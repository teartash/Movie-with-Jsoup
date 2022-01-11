package com.raj.jsoup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private parse_Adapter adapter;
    private ProgressBar progressBar;
    private ArrayList<parseItem >parseItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview =findViewById(R.id.recyclerview);
        progressBar=findViewById(R.id.progressbar);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new parse_Adapter(this,parseItems);
        recyclerview.setAdapter(adapter);

        Content content=new Content();
        content.execute();





    }

    private class Content extends AsyncTask<Void,Void,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));

        }

        @Override
        protected Void doInBackground(Void... voids){
            try {
                String url="https://www.cinemaqatar.com";
                Document doc=Jsoup.connect(url).get();
                Elements data=doc.select("span.thumbnail");
                int size=data.size();
                Log.d("size", "doInBackground: "+size);
                for (int i=0; i<size; i++){
                    String imgurl=data.select("span.thumbnail")
                            .select("noscript")
                            .select("img")
                            .eq(i)
                            .attr("src");
                    String title=data.select("h4.gridminfotitle")
                            .select("span")
                            .eq(i)
                            .text();
                    String language=data.select("p.gridminfo")
                            .eq(i)
                            .text();
                    String detialUrl=data.select("h4.gridminfotitle")
                            .select("a")
                            .eq(i)
                            .attr("href");
                    parseItems.add(new parseItem(imgurl,title,language,detialUrl));
                    Log.d("items","img : "+imgurl+". title: "+title +". language: "+language);

                }


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
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}