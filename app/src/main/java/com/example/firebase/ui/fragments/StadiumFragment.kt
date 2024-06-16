package com.example.firebase.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.R
import com.example.firebase.adapter.StadiumAdapter
import com.example.firebase.model.StadiumModel
import com.google.firebase.firestore.FirebaseFirestore


class StadiumFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val View: View= inflater.inflate(R.layout.fragment_stadium, container, false)
        val db = FirebaseFirestore.getInstance()
        var lstStadiums: List<StadiumModel>
        var rvStadium: RecyclerView = View.findViewById(R.id.rvStadium)

        db.collection("stadiums")
            //.whereEqualTo("name", "Camp Nou")
            .addSnapshotListener() { snap, error ->
                if (error != null) {
                    Log.e("Firestore Error", error.message.toString())
                    return@addSnapshotListener
                }
                lstStadiums = snap!!.documents.map {document ->

                    StadiumModel(
                        document["name"].toString(),
                        document["location"].toString(),
                        document["capacity"].toString(),
                        document["image"].toString()

                    )
                }
                rvStadium.adapter = StadiumAdapter(lstStadiums)
                rvStadium.layoutManager = LinearLayoutManager(requireContext())
            }


        return View
    }



}