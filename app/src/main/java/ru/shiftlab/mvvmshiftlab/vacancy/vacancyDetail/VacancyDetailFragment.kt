package ru.shiftlab.mvvmshiftlab.vacancy.vacancyDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.shiftlab.mvvmshiftlab.R
import ru.shiftlab.mvvmshiftlab.databinding.VacancyDetailFragmentBinding
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDatabase


class VacancyDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding: VacancyDetailFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.vacancy_detail_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val arguments =
            VacancyDetailFragmentArgs.fromBundle(
                requireArguments()
            )

        val dataSource = VacancyDatabase.getInstance(application).vacancyDao
        val viewModelFactory =
            VacancyDetailViewModelFactory(
                arguments.vacancyId,
                dataSource
            )

        val vacancyDetailViewModel =
            ViewModelProvider(this, viewModelFactory).get(VacancyDetailViewModel::class.java)

        binding.viewModel = vacancyDetailViewModel

        binding.lifecycleOwner = this


        vacancyDetailViewModel.navigateToVacancy.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    VacancyDetailFragmentDirections.actionVacancyDetailFragmentToVacancyFragment2()
                )
                vacancyDetailViewModel.doneNavigating()
            }
        })

        var respond = false
        val toolbar = activity?.findViewById<Toolbar>(R.id.toolbar)
        if (toolbar != null) {

            vacancyDetailViewModel.getTitleVacancy().observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    toolbar.title = it
                }
            })
        }

        binding.respondBtn.setOnClickListener {
            respond = respond.not()
            binding.respondBtn.text =
                if (respond) resources.getText(R.string.refuse) else resources.getText(R.string.respond)
            binding.statusVacancy.visibility = if (respond) View.VISIBLE else View.GONE
            if (respond) toolbar?.inflateMenu(R.menu.menu_respond_road) else toolbar?.menu?.clear()
        }

        return binding.root
    }
}
