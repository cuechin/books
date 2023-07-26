package com.hfad.secretmessage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class WelcomeFragment : Fragment() { // WelcomeFragment extends Fragment. This is what turns it from plain old class into a card-carrying fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? { // The class overrides onCreateView(). This method is called when the fragment needs to be displayed.
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        val startButton = view.findViewById<Button>(R.id.start)

        startButton.setOnClickListener{
            view.findNavController()
                .navigate(R.id.action_welcomeFragment_to_messageFragment)
        }

        return view // This tells Android that the fragment uses fragment_welcome.xml as its layout
    }
}