package ru.shiftlab.mvvmshiftlab.vacancy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.shiftlab.mvvmshiftlab.R
import ru.shiftlab.mvvmshiftlab.databinding.VacancyFragmentBinding
import ru.shiftlab.mvvmshiftlab.vacancy.adapter.VacancyAdapter
import ru.shiftlab.mvvmshiftlab.vacancy.adapter.VacancyListener
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDatabase
import ru.shiftlab.mvvmshiftlab.vacancy.viewmodel.VacancyViewModel
import ru.shiftlab.mvvmshiftlab.vacancy.viewmodel.VacancyViewModelFactory


class VacancyFragment : Fragment() {


    private lateinit var binding: VacancyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.vacancy_fragment, container, false
        )

        val application = requireNotNull(this.activity).application


        val dataSource = VacancyDatabase.getInstance(application).vacancyDao

        val viewModelFactory =
            VacancyViewModelFactory(
                dataSource,
                application
            )

        val vacancyViewModel =
            ViewModelProvider(this, viewModelFactory).get(VacancyViewModel::class.java)


        val adapter =
            VacancyAdapter(
                VacancyListener { vacancyId ->
                    Toast.makeText(context, "$vacancyId", Toast.LENGTH_LONG).show()
                    vacancyViewModel.onVacancyClicked(vacancyId)
                })

        binding.vacancyList.adapter = adapter


        vacancyViewModel.vacancies.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.lifecycleOwner = this

        binding.vacancy = vacancyViewModel


        vacancyViewModel.navigateToVacancyDetail.observe(viewLifecycleOwner, Observer { vacancyId ->
            vacancyId?.let {
                this.findNavController().navigate(
                    VacancyFragmentDirections.actionVacancyFragmentToVacancyDetailFragment(
                        vacancyId
                    )
                )
                vacancyViewModel.onVacancyDetailNavigated()
            }
        })

        return binding.root


    }


}
