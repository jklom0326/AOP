package fastcampas.aop.part2.aoppart3chapter06.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampas.aop.part2.aoppart3chapter06.R
import fastcampas.aop.part2.aoppart3chapter06.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentHomeBinding = FragmentHomeBinding.bind(view)
        binding = fragmentHomeBinding

        fragmentHomeBinding.articleRecyclerView.layoutManager = LinearLayoutManager(context)

    }
}