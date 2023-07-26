package com.hfad.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    // We're using three
    lateinit var stopwatch: Chronometer // The stopwatch.
    var running = false //Is the stopwatch running?
    var offset: Long = 0 //The base offset for the stopwatch.

    //Add key Strings for use with the Bundle. We're going to use these three constants as the names for any values we add to the Bundle.
    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get a reference to the stopwatch.
        stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        //Restore the previous state
        if (savedInstanceState != null) {
            // Restore the state of offset and running.
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            // set the time on the stopwatch and start it if it should be running.
            if (running){
                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            } else
                setBaseTime()
        }

        //The start button starts the stopwatch if it's not running
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            // Make sure the stopwatch starts from the correct time, then start it.
            if (!running) {
                setBaseTime()
                stopwatch.start() // The start() method makes the chronometer start counting from the "base" time
                running = true
            }
        }

        //The pause button pauses the stopwatch if it's running
        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            // Save the time on the stopwatch, then stop ot running.
            if (running) {
                saveOffset()
                stopwatch.stop() // This pause the chronometer so it stops counting.
                running = false
            }
        }

        //The reset button sets the offset and stopwatch to 0
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime() // Set the stopwatch time back to 0
        }
    }

    override fun onPause(){
        super.onPause()
        if (running) {
            saveOffset()
            stopwatch.stop()
        }
    }

    override fun onResume(){
        super.onResume()
        if (running) {
            setBaseTime()
            stopwatch.start()
            offset = 0
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong(OFFSET_KEY, offset)
        savedInstanceState.putBoolean(RUNNING_KEY, running)
        savedInstanceState.putLong(BASE_KEY, stopwatch.base)
        super.onSaveInstanceState(savedInstanceState)
    }

    // setBaseTime() and saveOffset() are convenience methods to make the code a bit more readable
    // Update the stopwatch.base time, allowing for any offset
    fun setBaseTime() {
        // The Chronometer "base" property is used to set the start time: the time from which the chronometer will start counting.
        // You set the start time to the current time by setting the "base" property to SystemClock.elapsedRealtime() using code like this: stopwatch.base = SystemClock.elapsedRealtime() <- This sets the time that's displayed to 0.
        // SystemClock.elapsedRealtime() returns the number of miliseconds since the device was booted. Setting the "base" property to this value means that the time that's displayed is set to 0.
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    // Record the offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
}