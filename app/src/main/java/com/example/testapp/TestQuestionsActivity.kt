package com.example.testapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class TestQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPosition: Int = 1;
    private var questionsList: ArrayList<Question>? = null;
    private var selectedOptionPosition: Int = 0;

    var progressBar: ProgressBar? = null
    var tvProgress: TextView? = null
    var tvQuestion: TextView? = null
    var tvAnswerOne: TextView? = null
    var tvAnswerTwo: TextView? = null
    var tvAnswerThree: TextView? = null
    var tvAnswerFour: TextView? = null
    var ivImage: ImageView? = null
    var btnSubmit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_questions)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        tvProgress = findViewById<TextView>(R.id.tv_progress)
        tvQuestion = findViewById<TextView>(R.id.tv_question)
        tvAnswerOne = findViewById<TextView>(R.id.tv_option_one)
        tvAnswerTwo = findViewById<TextView>(R.id.tv_option_two)
        tvAnswerThree = findViewById<TextView>(R.id.tv_option_three)
        tvAnswerFour = findViewById<TextView>(R.id.tv_option_four)
        ivImage = findViewById<ImageView>(R.id.iv_image)
        btnSubmit = findViewById<Button>(R.id.btn_submit)

        questionsList = Constants.getQuestions()
        setQuestion()

        tvAnswerOne!!.setOnClickListener(this)
        tvAnswerTwo!!.setOnClickListener(this)
        tvAnswerThree!!.setOnClickListener(this)
        tvAnswerFour!!.setOnClickListener(this)
        btnSubmit!!.setOnClickListener(this)
    }

    private fun setQuestion() {
        val question = questionsList!![currentPosition - 1]
        defaultOptionView()
        if(currentPosition == questionsList!!.size){
            btnSubmit!!.text = "Finish"
        }else{
            btnSubmit!!.text = "Answer"
        }

        progressBar!!.progress = currentPosition
        tvProgress!!.text = (currentPosition.toString() + "0%")
        tvQuestion!!.text = question!!.question;
        ivImage!!.setImageResource(question.image)
        tvAnswerOne!!.text = question.firstChoice
        tvAnswerTwo!!.text = question.secondChoice
        tvAnswerThree!!.text = question.thirdChoice
        tvAnswerFour!!.text = question.fourthChoice
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, tvAnswerOne!!)
        options.add(1, tvAnswerTwo!!)
        options.add(2, tvAnswerThree!!)
        options.add(3, tvAnswerFour!!)
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A7A7A"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.option_background
            )
        }

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvAnswerOne!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tvAnswerTwo!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tvAnswerThree!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tvAnswerFour!!.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionView()
        selectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#3C3C3C"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.option_selected_background
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(tvAnswerOne!!, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(tvAnswerTwo!!, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(tvAnswerThree!!, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(tvAnswerFour!!, 4)
            }
            R.id.btn_submit -> {
                if (selectedOptionPosition == 0) {
                    currentPosition++

                    when {
                        currentPosition <= questionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(this, "Test finished", Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    val question = questionsList?.get(currentPosition - 1)
                    if(question!!.correctChoice != selectedOptionPosition){
                        answerView(selectedOptionPosition, R.drawable.option_wrong_background)
                }
                    answerView(question.correctChoice, R.drawable.option_correct_background)

                    if(currentPosition == questionsList!!.size){
                        btnSubmit!!.text = "Finish"
                    }else{
                        btnSubmit!!.text = "Next Question"
                    }
                    selectedOptionPosition = 0
                }
        }
    }

}
}