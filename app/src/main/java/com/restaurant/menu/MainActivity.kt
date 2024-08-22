package com.restaurant.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.coin.login.extra.extra.restaurant.menu.databinding.ActivityMainBinding
import com.example.coin.login.extra.extra.restaurant.menu.domain.Order

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val coffePrice = 1.0
    private val chocolatePrice = 3.0
    private val breadPrice = 2.5
    private var orderPrice = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.root.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun updateOrderPrice(): List<Order> {
            val totalCoffe = (binding.inputQntCoffe.text.toString().toIntOrNull() ?: 0) * coffePrice
            val totalChocolate = (binding.inputQntChocolate.text.toString().toIntOrNull() ?: 0) * chocolatePrice
            val totalBread = (binding.inputQntBread.text.toString().toIntOrNull() ?: 0) * breadPrice
            val orderPrice = totalCoffe + totalChocolate + totalBread

            val breadQnt = binding.inputQntBread.text.toString().toIntOrNull() ?: 0
            val chocolateQnt = binding.inputQntChocolate.text.toString().toIntOrNull() ?: 0
            val coffeQnt = binding.inputQntCoffe.text.toString().toIntOrNull() ?: 0

            val orders = mutableListOf<Order>()

            if (totalCoffe > 0) orders.add(Order("Coffe", coffeQnt, coffePrice, totalCoffe))
            if (totalChocolate > 0) orders.add(Order("Chocolate", chocolateQnt, chocolatePrice, totalChocolate))
            if (totalBread > 0) orders.add(Order("Bread", breadQnt, breadPrice, totalBread))

            binding.totalPrice.text = "Total: ${orderPrice}€"

            return orders
        }

        binding.coffePrice.text = "${coffePrice}€"
        binding.chocolatePrice.text = "${chocolatePrice}€"
        binding.breadPrice.text = "${breadPrice}€"


        binding.orderButton.setOnClickListener {
            val i = Intent(this, SplashScreenActivity::class.java)
            i.putExtra("orders", ArrayList(updateOrderPrice()))
            startActivity(i)
        }

        binding.coffeCheck.setOnClickListener {
            if (binding.coffeCheck.isChecked) {
                binding.inputQntCoffe.setText("1")
                binding.coffePrice.visibility = View.VISIBLE
                updateOrderPrice()

                binding.inputQntCoffe.addTextChangedListener {
                    val inputQntCoffe = binding.inputQntCoffe.text.toString().toIntOrNull() ?: 0
                    val totalCoffe = inputQntCoffe * coffePrice
                    binding.coffePrice.text = "${totalCoffe}€"
                    updateOrderPrice()
                }
            } else {
                binding.inputQntCoffe.setText("")
                binding.coffePrice.visibility = View.GONE
                updateOrderPrice()
            }
        }

        binding.chocolateCheck.setOnClickListener {
            if (binding.chocolateCheck.isChecked) {
                binding.inputQntChocolate.setText("1")
                binding.chocolatePrice.visibility = View.VISIBLE
                updateOrderPrice()

                binding.inputQntChocolate.addTextChangedListener {
                    val inputQntChocolate =
                        binding.inputQntChocolate.text.toString().toIntOrNull() ?: 0
                    val totalChocolate = inputQntChocolate * chocolatePrice
                    binding.chocolatePrice.text = "${totalChocolate}€"
                    updateOrderPrice()
                }

            } else {
                binding.inputQntChocolate.setText("")
                binding.chocolatePrice.visibility = View.GONE
                updateOrderPrice()
            }
        }
        binding.breadCheck.setOnClickListener {
            if (binding.breadCheck.isChecked) {
                binding.inputQntBread.setText("1")
                binding.breadPrice.visibility = View.VISIBLE
                updateOrderPrice()

                binding.inputQntBread.addTextChangedListener {
                    val inputQntBread = binding.inputQntBread.text.toString().toIntOrNull() ?: 0
                    val totalBread = inputQntBread * breadPrice
                    binding.breadPrice.text = "${totalBread}€"
                    updateOrderPrice()
                }

            } else {
                binding.inputQntBread.setText("")
                binding.breadPrice.visibility = View.GONE
                orderPrice += 0.0
                updateOrderPrice()
            }
        }

    }
}