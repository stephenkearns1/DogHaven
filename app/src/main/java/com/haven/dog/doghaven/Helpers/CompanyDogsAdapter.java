package com.haven.dog.doghaven.Helpers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.vision.text.Text;
import com.haven.dog.doghaven.Activities.CompanyDogActivity;
import com.haven.dog.doghaven.Activities.UserProfile;
import com.haven.dog.doghaven.Models.Dog;
import com.haven.dog.doghaven.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephen Kearns on 10/12/2016.
 */

public class CompanyDogsAdapter extends RecyclerView.Adapter<CompanyDogsAdapter.ViewHolder> {
    private List<Dog> dogList;
    private Context context;
    private PopupWindow mPopupWindow;
    private RelativeLayout mRelativeLayout;
    private CompanyDogActivity companyDogs;
    private int dog_id;
    private final String doghavenAPI_URL = "https://doghaven-backend-app-stephenkearns1.c9users.io/index.php";

    public CompanyDogsAdapter(Context context){
        this.context = context;
        dogList = new ArrayList<>();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nametv, breedtv, agetv, colortv;
        public Button physicalBtn, behaviourBtn, socialBtn, medicalBtn, deleteBtn;


        /*

           Uses the viewholder pattern to hold reference to views

         */


        public ViewHolder(View v) {
            super(v);
            nametv = (TextView) v.findViewById(R.id.dogname_tv);
            breedtv = (TextView) v.findViewById(R.id.dogbreed_tv);
            agetv = (TextView) v.findViewById(R.id.dogage_tv);
            colortv = (TextView) v.findViewById(R.id.dogcolor_tv);
            physicalBtn = (Button) v.findViewById(R.id.physocalAttrBtn);
            behaviourBtn = (Button) v.findViewById(R.id.behaviourAttrBtn);
            socialBtn = (Button) v.findViewById(R.id.socialAttrBtn);
            deleteBtn = (Button) v.findViewById(R.id.deleteDog_btn);


        }


    }




    @Override
    public CompanyDogsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.company_dog_row, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    /*
        When the view is been binding get referance and set elements with custom data based on the dogs
     */

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
            final Dog dogModel = dogList.get(position);
            holder.nametv.setText(dogModel.getName());
            holder.breedtv.setText(dogModel.getBreed());
            holder.agetv.setText(dogModel.getAge());
            holder.colortv.setText(dogModel.getColor());
        holder.physicalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Physical Atrributes");
                builder.setMessage("Size: " + dogModel.getSize() + "\n" +
                        "Fur: " + dogModel.getFur()+ "\n" +
                        "Body: " + dogModel.getBody()+ "\n" +
                        "Tolerance: " + dogModel.getTolerance()+ "\n" +
                        "Neutered: " + dogModel.getNeutered())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                builder.show();


            }
        });

        holder.behaviourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Behaviour Atrributes");
                builder.setMessage("Energy: " + dogModel.getEnergy() + "\n" +
                        "Exercise: " + dogModel.getExercise()+ "\n" +
                        "Intelligence: " +dogModel.getIntelligence() + "\n" +
                        "Playful: " + dogModel.getPlayful()+ "\n" +
                        "Instinct: " +dogModel.getInstinct())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                builder.show();
            }
        });

        holder.socialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Social Atrributes");
                builder.setMessage("People: " + dogModel.getPeople() + "\n" +
                        "Family: " + dogModel.getFamily()+ "\n" +
                        "Dogs: " + dogModel.getDogs()+ "\n" +
                        "Emotion: " +dogModel.getEmotion() + "\n" +
                        "Sociability: " + dogModel.getSociality())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                builder.show();
            }
        });



        holder.deleteBtn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Dog dog = dogList.get(position);
                dog_id = dog.getDogId();
                DeleteDog();
               RemoveDog(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }


    public void addDog(Dog newDog, int position){
        dogList.add(newDog);
        notifyItemInserted(position);
    }

    public void AddAllDogs(List<Dog> newDogs){
        dogList.addAll(newDogs);
        notifyDataSetChanged();
    }

    public void ClearAll(){

        if(dogList != null && !dogList.isEmpty()){
            dogList.clear();
            notifyDataSetChanged();
        }
    }

    public void RemoveDog(int position){
        dogList.remove(position);
        notifyItemRemoved(position);
    }



    /*
        When a dog has been deleted, make a request to also delete the dog from the database.
     */
    public void DeleteDog(){

        StringRequest deleteDogRequest  = new StringRequest(Request.Method.POST,  doghavenAPI_URL  ,
                new Response.Listener<String>() {



                    @Override
                    public void onResponse(String response) {
                        //check the response from the server
                        Log.i("New Response", response.toString());


                        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Deleted")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {


                                    }
                                });

                        builder.show();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Error: " + error.toString())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        });

                builder.show();
            }
        }) {

            @Override
            protected Map<String, String> getParams ()throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //sending login signals to server that it is a login request and should handle accordingly
                params.put("deletedog", "deletedog");
                params.put("dog_id","" + dog_id);
                return params;
            }
        };




        // Adding the request to the queue
        MyNetworkingSingletonVolley.getInstance(context).addReuestToQueue(deleteDogRequest);
    }


}
