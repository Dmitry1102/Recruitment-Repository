package pl.proexe.junior.view.epg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import pl.proexe.junior.databinding.InformationFragmentBinding
import pl.proexe.junior.view.epg.EpgActivity.Companion.DATA
import pl.proexe.junior.view.epg.EpgActivity.Companion.IMAGE
import pl.proexe.junior.view.epg.EpgActivity.Companion.INFO_PROFILE
import pl.proexe.junior.view.epg.EpgUtils.pressedButton

class EpgFragment: Fragment() {

    private var _binding: InformationFragmentBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = InformationFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments

        val info = bundle?.getString(IMAGE)
        val name = bundle?.getString(INFO_PROFILE)
        val value = bundle?.getLong(DATA)

        if (bundle != null){
            binding.ivLogo.load(info)
            binding.tvAccount.text = name
            binding.tvValue.text = value.toString()
        }

        binding.ivMain.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        binding.tvMain.setOnClickListener {
            pressedButton(binding.tvMain)
        }

        binding.tvFavourite.setOnClickListener {
            pressedButton(binding.tvFavourite)
        }

        binding.tvChannels.setOnClickListener {
            pressedButton(binding.tvChannels)
        }

        binding.tvFavourite.setOnClickListener {
            pressedButton(binding.tvFavourite)
        }

        binding.tvRecord.setOnClickListener {
            pressedButton(binding.tvRecord)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}