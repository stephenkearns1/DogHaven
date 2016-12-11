package com.haven.dog.doghaven.Helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haven.dog.doghaven.Models.Dog;
import com.haven.dog.doghaven.R;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * Created by Stephen Kearns on 10/12/2016.
 */

public class CompanyDogsAdapter extends RecyclerView.Adapter<CompanyDogsAdapter.ViewHolder> {
    private List<Dog> dogList;
    private Context context;

    public CompanyDogsAdapter(Context context){
        this.context = context;
        dogList = new ArrayList<>();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nametv, breedtv, agetv, colortv, sizetv, furtv, bodytv, tolerancetv, neuteredtv;

        public ViewHolder(View v){
              super(v);
              nametv = (TextView) v.findViewById(R.id.dogname_tv);
              breedtv = (TextView) v.findViewById(R.id.dogbreed_tv);
              agetv = (TextView) v.findViewById(R.id.dogage_tv);
              colortv = (TextView) v.findViewById(R.id.dogcolor_tv);
              sizetv =(TextView) v.findViewById(R.id.size);
              furtv = (TextView) v.findViewById(R.id.fur);
              bodytv = (TextView) v.findViewById(R.id.body);
              tolerancetv = (TextView) v.findViewById(R.id.tolerance);
              neuteredtv = (TextView) v.findViewById(R.id.neutered);


        }
    }


    @Override
    public CompanyDogsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.company_dog_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            final Dog dogModel = dogList.get(position);
            holder.nametv.setText(dogModel.getName());
            holder.breedtv.setText(dogModel.getBreed());
            holder.agetv.setText(dogModel.getAge());
            holder.colortv.setText(dogModel.getColor());
            holder.sizetv.setText(dogModel.getSize());
            holder.furtv.setText(dogModel.getFur());
            holder.bodytv.setText(dogModel.getBody());
            holder.tolerancetv.setText(dogModel.getTolerance());
            holder.neuteredtv.setText(dogModel.getNeutered());

    }

    @Override
    public int getItemCount() {
        return dogList.size();
    }


    public void addDog(Dog newDog){
        dogList.add(newDog);
        notifyDataSetChanged();
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
}
