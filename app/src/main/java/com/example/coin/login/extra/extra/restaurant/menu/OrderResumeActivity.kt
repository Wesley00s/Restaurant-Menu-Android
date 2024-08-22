package com.example.coin.login.extra.extra.restaurant.menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.coin.login.extra.extra.restaurant.menu.databinding.ActivityOrderResumeBinding
import com.example.coin.login.extra.extra.restaurant.menu.domain.Order

class OrderResumeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderResumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOrderResumeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.root.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val i = intent.extras
        val orders = i?.getSerializable("orders") as? ArrayList<Order> ?: arrayListOf()
        var orderPrice = 0.0

        for (order in orders) orderPrice += order.total

        for (order in orders) {
            binding.resume.append(
                """
                    |
                | ${order.name.uppercase()}
                | Unit price: ${order.price}€
                | Order quantity: ${order.quantity}
                | Total in ${order.name}: ${order.total}€
                | 
                | """.trimMargin()
            )

        }
        binding.resume.append("Total Order: ${orderPrice}€")
    }
}