package ru.alexeyp.searchrepo.utils.base;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableInt;

import ru.alexeyp.searchrepo.utils.State;

public class LCEViewModel extends ViewModel {

    private ObservableInt _obsState;
    private MutableLiveData<ObservableInt> _state;
    private Throwable _error;

    public LCEViewModel() {
        _obsState = new ObservableInt();
        _state = new MutableLiveData<>();
        _error = null;
        _state.setValue(_obsState);
    }

    protected void setContentState(){
        changeState(State.CONTENT);
    }

    protected void setProgressState(){
        changeState(State.PROGRESS);
    }

    protected void setErrorState(Throwable error){
        changeState(State.ERROR);
        _error = error;
    }

    protected void setState(int state) {
        changeState(state);
    }

    public MutableLiveData<ObservableInt> getState() {
        return _state;
    }

    protected Throwable getError() {
        return _error;
    }

    private void changeState(int state) {
        _obsState.set(state);
        _state.setValue(_obsState);
    }
}
