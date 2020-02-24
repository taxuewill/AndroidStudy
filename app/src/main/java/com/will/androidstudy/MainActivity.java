package com.will.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.oxo42.stateless4j.delegates.Action1;
import com.github.oxo42.stateless4j.delegates.Action2;
import com.github.oxo42.stateless4j.transitions.Transition;
import com.github.oxo42.stateless4j.triggers.TriggerWithParameters;
import com.github.oxo42.stateless4j.triggers.TriggerWithParameters1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private static final String TAG = "StateLess4J";

    StateMachineConfig<String, String> mConfig = new StateMachineConfig<>();
    StateMachine<String, String> mStateMachine;

    String mStateA = "A";
    String mStateB = "B";
    String mStateBChild1 = "B-1";
    String mStateAChild1 = "A-1";

    String mTriger2B = "triger2b";
    String mTriger2Bchild1 = "triger2bchild1";
    String mTriggerA12B = "a1Trigger2b";

    Button mTest;
    HandlerThread mBackthread = new HandlerThread("back");
    Handler mBackHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStateMachine();
        mTest = findViewById(R.id.btnTest);
        mTest.setOnClickListener(this);
        mBackthread.start();
        mBackHandler = new Handler(mBackthread.getLooper());


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();

    void initStateMachine(){
        mConfig.configure(mStateA)
            .onEntry(new Action1<Transition<String, String>>() {
                @Override
                public void doIt(Transition<String, String> arg1) {
                    Log.i(TAG,"enter StateA "+arg1.getSource()+"-> "+arg1.getDestination());
                }
            })
            .onExit(new Action1<Transition<String, String>>() {
                @Override
                public void doIt(Transition<String, String> arg1) {
                    Log.i(TAG,"leave StateA "+arg1.getSource()+"-->"+ arg1.getDestination());
                }
            })
            .permit(mTriger2B,mStateB)
            .permit(mTriger2Bchild1,mStateBChild1);
        mConfig.configure(mStateAChild1)
                .substateOf(mStateA)
                .onEntry(new Action1<Transition<String, String>>() {
                    @Override
                    public void doIt(Transition<String, String> arg1) {
                        Log.i(TAG,"enter StateA1 "+arg1.getSource()+"->"+ arg1.getDestination());
                    }
                })
                .onExit(new Action1<Transition<String, String>>() {
                    @Override
                    public void doIt(Transition<String, String> arg1) {
                        Log.i(TAG,"leave StateA1 "+arg1.getSource()+" --> "+ arg1.getDestination());
                    }
                })
                .permit(mTriggerA12B,mStateB);
        mConfig.configure(mStateB)
                .onEntry(new Action1<Transition<String, String>>() {
                    @Override
                    public void doIt(Transition<String, String> arg1) {
                        Log.i(TAG,"enter StateB "+arg1.getSource()+","+arg1.getDestination());
                    }
                })
                .onEntry(new Action2<Transition<String, String>, Object[]>() {
                    @Override
                    public void doIt(Transition<String, String> arg1, Object[] arg2) {
                        Log.i(TAG,"enter StateB "+arg1.getSource()+","+arg1.getDestination());
                        String param = (String)arg2[0];
                        Log.i(TAG,"param is "+param);
                    }
                })
                .onExit(new Action1<Transition<String, String>>() {
                    @Override
                    public void doIt(Transition<String, String> arg1) {
                        Log.i(TAG,"leave StateB "+arg1.getSource()+"->"+ arg1.getDestination());
                    }
                });
        mConfig.configure(mStateBChild1)
                .substateOf(mStateB)
                .onEntry(new Action1<Transition<String, String>>() {
                    @Override
                    public void doIt(Transition<String, String> arg1) {
                        Log.i(TAG,"enter StateBChild1 "+arg1.getSource()+"->"+ arg1.getDestination());
                    }
                })
                .onExit(new Action1<Transition<String, String>>() {
                    @Override
                    public void doIt(Transition<String, String> arg1) {
                        Log.i(TAG,"leave StateBChild1 "+arg1.getSource()+"->"+ arg1.getDestination());
                    }
                });
        mStateMachine = new StateMachine<>(mStateAChild1,mConfig);
    }

    @Override
    public void onClick(View v) {
        mBackHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    TriggerWithParameters1<String,String> triggerWithParameters1 = new TriggerWithParameters1<>(mTriger2B,String.class);
                    mStateMachine.fire(triggerWithParameters1,"Hello");
                }catch (IllegalStateException e){
                    Log.i(TAG,"illegal jump");
                }

            }
        });

    }
}
