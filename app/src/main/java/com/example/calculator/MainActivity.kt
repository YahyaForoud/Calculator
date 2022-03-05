package com.example.calculator

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.calculator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import net.objecthunter.exp4j.ExpressionBuilder

/**
 * Created by Yahya Foroud on Mar 05 2022
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //set toolbar
        val toolbar = binding.toolbarMain
        setSupportActionBar(toolbar)

        changeTheme()

        onNumberClicked()

        onOperatorClicked()

    }

    //spring animation
    @SuppressLint("ClickableViewAccessibility")
    private fun View.implementSpringAnimationTrait() {
        val scaleXAnim = SpringAnimation(this, DynamicAnimation.SCALE_X, 0.90f)
        val scaleYAnim = SpringAnimation(this, DynamicAnimation.SCALE_Y, 0.90f)

        setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    scaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                    scaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                    scaleXAnim.start()

                    scaleYAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                    scaleYAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                    scaleYAnim.start()

                }
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> {
                    scaleXAnim.cancel()
                    scaleYAnim.cancel()
                    val reverseScaleXAnim = SpringAnimation(this, DynamicAnimation.SCALE_X, 1f)
                    reverseScaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                    reverseScaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                    reverseScaleXAnim.start()

                    val reverseScaleYAnim = SpringAnimation(this, DynamicAnimation.SCALE_Y, 1f)
                    reverseScaleYAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                    reverseScaleYAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                    reverseScaleYAnim.start()

                }
            }

            false
        }
    }

    //change theme
    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeTheme() {
        binding.switchMain.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchMain.tooltipText = "Day mode"
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchMain.tooltipText = "Night mode"
            }
        }

    }

    private fun onNumberClicked() {

        binding.zeroMainBtn.setOnClickListener {
            if (binding.expressionMainTv.text.isNotEmpty()) {
                appendText("0")
            }
        }

        binding.oneMainBtn.setOnClickListener {
            appendText("1")
            binding.acMainBtn.text = "c"
        }

        binding.twoMainBtn.setOnClickListener {
            appendText("2")
            binding.acMainBtn.text = "c"
        }

        binding.treeMainBtn.setOnClickListener {
            appendText("3")
            binding.acMainBtn.text = "c"
        }

        binding.fourMainBtn.setOnClickListener {
            appendText("4")
            binding.acMainBtn.text = "c"
        }

        binding.fiveMainBtn.setOnClickListener {
            appendText("5")
            binding.acMainBtn.text = "c"
        }

        binding.sixMainBtn.setOnClickListener {
            appendText("6")
            binding.acMainBtn.text = "c"
        }

        binding.sevenMainBtn.setOnClickListener {
            appendText("7")
            binding.acMainBtn.text = "c"
        }

        binding.eightMainBtn.setOnClickListener {
            appendText("8")
            binding.acMainBtn.text = "c"
        }

        binding.nineMainBtn.setOnClickListener {
            appendText("9")
            binding.acMainBtn.text = "c"
        }

        binding.dotMainBtn.setOnClickListener {
            if (binding.expressionMainTv.text.isEmpty() || binding.answerMainTv.text.isNotEmpty()) {
                appendText("0.")
            } else if (!binding.expressionMainTv.text.contains(".")) {
                appendText(".")
            }
            binding.acMainBtn.text = "c"

        }

    }

    @SuppressLint("SetTextI18n")
    private fun onOperatorClicked() {
        binding.acMainBtn.setOnClickListener {
            binding.expressionMainTv.text = ""
            binding.answerMainTv.text = ""
        }

        binding.bracesLeftMainBtn.setOnClickListener {
            appendText("(")
            binding.acMainBtn.text = "c"
        }

        binding.bracesRightMainBtn.setOnClickListener {
            appendText(")")
            binding.acMainBtn.text = "c"
        }

        binding.divisionMainBtn.setOnClickListener {
            if (binding.expressionMainTv.text.isNotEmpty()) {
                val myChar = binding.expressionMainTv.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("/")
                }
            }
        }

        binding.totalMainBtn.setOnClickListener {
            if (binding.expressionMainTv.text.isNotEmpty()) {
                val myChar = binding.expressionMainTv.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("+")
                }
            }
        }

        binding.minusMainBtn.setOnClickListener {
            if (binding.expressionMainTv.text.isNotEmpty()) {
                val myChar = binding.expressionMainTv.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("-")
                }
            }
        }

        binding.multiplicationMainBtn.setOnClickListener {
            if (binding.expressionMainTv.text.isNotEmpty()) {
                val myChar = binding.expressionMainTv.text.last()
                if (myChar != '+' && myChar != '-' && myChar != '*' && myChar != '/') {
                    appendText("*")
                }
            }
        }

        binding.clearMainBtn.setOnClickListener {
            val oldText = binding.expressionMainTv.text.toString()
            if (oldText.isNotEmpty()) {
                binding.expressionMainTv.text = oldText.substring(0, oldText.length - 1)
            }

        }

        binding.equalMainBtn.setOnClickListener {
            binding.equalMainBtn.implementSpringAnimationTrait()
            if (binding.expressionMainTv.text.isNotEmpty()) {
                try {
                    val expression =
                        ExpressionBuilder(binding.expressionMainTv.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    // 135.0 == 135 : convert float to integer
                    if (result == longResult.toDouble()) {
                        binding.answerMainTv.text = longResult.toString()
                    } else {
                        binding.answerMainTv.text = result.toString()
                    }

                } catch (e: Exception) {
                    binding.expressionMainTv.text = ""
                    binding.answerMainTv.text = ""
                    Snackbar.make(it, "What you did is wrong", Snackbar.LENGTH_SHORT)
                        .setAction("Try again") {

                        }.show()
                }
            }

            binding.acMainBtn.text = "Ac"

        }

    }

    private fun appendText(newText: String) {
        if (binding.answerMainTv.text.isNotEmpty()) {
            binding.expressionMainTv.text = ""
        }

        binding.answerMainTv.text = ""
        binding.expressionMainTv.append(newText)

        val viewTree: ViewTreeObserver = binding.horizontalScrollViewExpressionTv.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewExpressionTv.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewExpressionTv.scrollTo(binding.expressionMainTv.width, 0)
            }

        })
    }

}