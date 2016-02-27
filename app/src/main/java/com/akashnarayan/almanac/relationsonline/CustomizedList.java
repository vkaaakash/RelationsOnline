package com.akashnarayan.almanac.relationsonline;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CustomizedList extends ArrayAdapter<String>{
    ImageView pics;
    private final Activity context;
    private final String[] names;
    private final String[] relations;
    private final Integer[] ImageIds;
    public Firebase myFirebaseRef;
    TextView nameoftheperson;
    TextView relationships;
    int pos;

    public CustomizedList(Activity context,String[] names,String[] relations,Integer[] ImageIDs){
        super(context, R.layout.row,names);
        this.context=context;
        this.names=names;
        this.relations=relations;
        this.ImageIds=ImageIDs;
        Firebase.setAndroidContext(getContext());
        myFirebaseRef = new Firebase("https://relationmonikaalmanac.firebaseio.com/");


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View rowview;
        rowview = inflater.inflate(R.layout.row,null,true);
        pos=position;

        nameoftheperson = (TextView) rowview.findViewById(R.id.nameOfPerson);
        relationships=(TextView) rowview.findViewById(R.id.relationOfPerson);
        pics = (ImageView) rowview.findViewById(R.id.imageView);

        nameoftheperson.setText(names[pos]);
        relationships.setText(relations[pos]);
        pics.setImageResource(ImageIds[pos]);


        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                nameoftheperson.setText(String.valueOf(dataSnapshot.child("relations").child(String.valueOf(pos)).child("Name").getValue()));
                relationships.setText(String.valueOf(dataSnapshot.child("relations").child(String.valueOf(pos)).child("Relation").getValue()));
                Picasso.with(getContext()).load(String.valueOf(dataSnapshot.child("relations").child(String.valueOf(pos)).child("Image").getValue())).into(pics);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return rowview;



    }

}

