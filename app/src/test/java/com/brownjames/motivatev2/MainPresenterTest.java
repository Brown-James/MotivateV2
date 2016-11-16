package com.brownjames.motivatev2;

import android.widget.EditText;
import android.widget.Toast;

import com.brownjames.motivatev2.activity.main.MVP_Main;
import com.brownjames.motivatev2.activity.main.model.MainModel;
import com.brownjames.motivatev2.activity.main.presenter.MainPresenter;
import com.brownjames.motivatev2.data.Task;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.defaultanswers.ReturnsDeepStubs;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;

import dalvik.annotation.TestTarget;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by james on 11/11/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
public class MainPresenterTest {
    private MainPresenter mPresenter;
    private MainModel mockModel;
    private MVP_Main.RequiredViewOps mockView;

    // To test the Presenter you can just
    // create the object and pass the Model and View mocks
    @Before
    private void setup() {
        // Creating the mocks
        mockView = Mockito.mock(MVP_Main.RequiredViewOps.class);
        mockModel = Mockito.mock(MainModel.class, RETURNS_DEEP_STUBS);

        // Pass the mocks to the Presenter instance
        mPresenter = new MainPresenter(mockView);
        mPresenter.setModel(mockModel);

        // Define the value to be returned by the Model
        // when loading data
        when(mockModel.loadData()).thenReturn(true);
        reset(mockView);
    }

    @Test
    public void testClickNewTask() {
        // We need to mock an EditText
        EditText mockEditText = Mockito.mock(EditText.class, RETURNS_DEEP_STUBS);

        // The mock should return a String
        when(mockEditText.getText().toString()).thenReturn("Test_true");

        // We also define a fake position to be returned by the insertNote method in Model
        int arrayPos = 10;
        when(mockModel.insertTask(any(Task.class))).thenReturn(arrayPos);

        mPresenter.clickNewTask(mockEditText);
        verify(mockModel).insertTask(any(Task.class));
        verify(mockView).notifyItemInserted(eq(arrayPos+1));
        verify(mockView).notifyItemRangeChanged(eq(arrayPos), anyInt());
        verify(mockView, never()).showToast(any(Toast.class));
    }

}
