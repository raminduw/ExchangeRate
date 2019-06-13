package com.ramindu.weeraman.currencyrate;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;
import com.ramindu.weeraman.currencyrate.model.ExchangeRate;
import com.ramindu.weeraman.currencyrate.repository.ExchangeRateRepository;
import com.ramindu.weeraman.currencyrate.viewmodel.ExchangeRateViewModel;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.ArgumentMatchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ExchangeRateViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    ExchangeRateRepository exchangeRateRepository;

    @Before
    public void before() throws Exception {
        exchangeRateRepository = mock(ExchangeRateRepository.class);
    }



    @Test
    public void testRetrieveExchangeRateSuccess() {

        ExchangeRate exchangeRate ;
        exchangeRate = mock(ExchangeRate.class);
        when(exchangeRateRepository.getExchangeRate())
                .thenReturn(Observable.just(exchangeRate));
        ExchangeRateViewModel viewModel = new ExchangeRateViewModel(exchangeRateRepository, Schedulers.trampoline(),
                Schedulers.trampoline());
        assertEquals(false,viewModel.isLoading().getValue());
        assertNull(viewModel.getErrorMessage().getValue());
    }

    @Test
    public void testRetrieveExchangeRateError() {

        Exception exception = new Exception();
        when(exchangeRateRepository.getExchangeRate())
                .thenReturn(Observable.<ExchangeRate>error(exception));

        ExchangeRateViewModel viewModel = new ExchangeRateViewModel(exchangeRateRepository, Schedulers.trampoline(),
                Schedulers.trampoline());
        assertEquals(false,viewModel.isLoading().getValue());
        assertNull(viewModel.getExchangeRate().getValue());
    }


    @Test
    public void testRetrieveExchangeRateErrorLivedataNoExchangeRateChange() {
        Exception exception = new Exception();
        when(exchangeRateRepository.getExchangeRate())
                .thenReturn(Observable.<ExchangeRate>error(exception));

        ExchangeRateViewModel viewModel = new ExchangeRateViewModel(exchangeRateRepository, Schedulers.trampoline(),
                Schedulers.trampoline());

        Observer<ExchangeRate> mockLiveDataObserver = mock(Observer.class);
        viewModel.getExchangeRate().observeForever(mockLiveDataObserver);
        verify(mockLiveDataObserver,times(0)).onChanged(ArgumentMatchers.<ExchangeRate>any());
    }


    @Test
    public void testRetrieveExchangeRateSuccessLivedataNoErrorChange() {

        ExchangeRate exchangeRate ;
        exchangeRate = mock(ExchangeRate.class);
        when(exchangeRateRepository.getExchangeRate())
                .thenReturn(Observable.just(exchangeRate));
        ExchangeRateViewModel viewModel = new ExchangeRateViewModel(exchangeRateRepository, Schedulers.trampoline(),
                Schedulers.trampoline());
        Observer<String> mockLiveDataObserver = mock(Observer.class);
        viewModel.getErrorMessage().observeForever(mockLiveDataObserver);

        verify(mockLiveDataObserver,times(0)).onChanged(anyString());
    }

   /* @Test
    public void testRetrieveExchangeRateSuccessLivedataExchangeRateChange() {
        ExchangeRate exchangeRate ;
        exchangeRate = mock(ExchangeRate.class);
        when(exchangeRateRepository.getExchangeRate())
                .thenReturn(Observable.just(exchangeRate));
        ExchangeRateViewModel viewModel = new ExchangeRateViewModel(exchangeRateRepository, Schedulers.trampoline(),
                Schedulers.trampoline());
        Observer<ExchangeRate> mockLiveDataObserver = mock(Observer.class);
        viewModel.getExchangeRate().observeForever(mockLiveDataObserver);

        verify(mockLiveDataObserver,times(1)).onChanged(ArgumentMatchers.<ExchangeRate>any());
    }*/



}
