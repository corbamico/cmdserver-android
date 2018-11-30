package com.github.corbamico.ssrunning



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.corbamico.ssrunning.data.ServerStatus
import com.github.corbamico.ssrunning.data.Status
import com.github.corbamico.ssrunning.model.ServerViewModel
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {
    private lateinit var viewModel: ServerViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =  ViewModelProviders.of(this).get(ServerViewModel::class.java)
        viewModel.serverStatus.observe(this, Observer {
            text_server_status.text = it.status.toString()

            when(it.status){
                Status.Checking ->{

                    text_server_status.setTextColor(resources.getColor(R.color.bootstrap_gray_dark))
                    btn_restart.isEnabled = false
                }
                Status.Unknown ->{
                    swipeRefresh.isRefreshing = false
                    text_server_status.setTextColor(resources.getColor(R.color.bootstrap_gray_dark))
                    btn_restart.isEnabled = false
                }
                Status.Running ->{
                    swipeRefresh.isRefreshing = false
                    text_server_status.setTextColor(resources.getColor(R.color.colorDeepSkyBlue))
                    btn_restart.isEnabled = true
                }
                Status.Idle->{
                    swipeRefresh.isRefreshing = false
                    text_server_status.setTextColor(resources.getColor(R.color.bootstrap_gray_dark))
                    btn_restart.isEnabled = true
                }
            }
        })

        btn_restart.setOnClickListener {
            viewModel.serverStatus.value = ServerStatus(Status.Checking)
            viewModel.retriveSever()
        }
        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)

        swipeRefresh.setOnRefreshListener {
            viewModel.serverStatus.value = ServerStatus(Status.Checking)
            viewModel.retriveSever()
        }

        viewModel.retriveSever()
    }
}
