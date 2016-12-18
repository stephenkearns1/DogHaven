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

import com.google.android.gms.vision.text.Text;
import com.haven.dog.doghaven.Activities.UserProfile;
import com.haven.dog.doghaven.Models.Dog;
import com.haven.dog.doghaven.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kearn on 16/12/2016.
 */


    /**
     * Created by Stephen Kearns on 10/12/2016.
     */

    public class MatchDogsAdapter  extends RecyclerView.Adapter<com.haven.dog.doghaven.Helpers.MatchDogsAdapter .ViewHolder> {
        private List<Dog> dogList;
        private Context context;
        private PopupWindow mPopupWindow;
        private RelativeLayout mRelativeLayout;

        public MatchDogsAdapter (Context context){
            this.context = context;
            dogList = new ArrayList<>();
        }



        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView nametv, breedtv, agetv, colortv;
            public Button physicalBtn, behaviourBtn, socialBtn, medicalBtn;


            public ViewHolder(View v) {
                super(v);
                nametv = (TextView) v.findViewById(R.id.dogname_tv);
                breedtv = (TextView) v.findViewById(R.id.dogbreed_tv);
                agetv = (TextView) v.findViewById(R.id.dogage_tv);
                colortv = (TextView) v.findViewById(R.id.dogcolor_tv);
                physicalBtn = (Button) v.findViewById(R.id.physocalAttrBtn);
                behaviourBtn = (Button) v.findViewById(R.id.behaviourAttrBtn);
                socialBtn = (Button) v.findViewById(R.id.socialAttrBtn);




            }


        }




        @Override
        public com.haven.dog.doghaven.Helpers.MatchDogsAdapter .ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(context).inflate(R.layout.matched_dog_row, parent, false);
            com.haven.dog.doghaven.Helpers.MatchDogsAdapter .ViewHolder vh = new com.haven.dog.doghaven.Helpers.MatchDogsAdapter .ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(com.haven.dog.doghaven.Helpers.MatchDogsAdapter .ViewHolder holder, final int position) {
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
                    builder.setTitle("Physical Atrributes");
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
                    builder.setTitle("Physical Atrributes");
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




        }

        @Override
        public int getItemCount() {
            return dogList.size();
        }


        public void addDog(Dog newDog,int position){
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

        public void Remove(int position){
            dogList.remove(position);
            notifyItemRemoved(position);
        }
    }

