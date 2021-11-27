package si.fri.prpo.projekt.prestreznik;

import si.fri.prpo.projekt.anotacija.BeleziKlice;
import si.fri.prpo.projekt.zrno.BelezenjeKlicevZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@BeleziKlice
public class BelezenjeKlicevInterceptor {

    @Inject
    private BelezenjeKlicevZrno belezenjeKlicevZrno;

    @AroundInvoke
    public Object beleziKlice(InvocationContext ctx) throws Exception{

        //System.out.println(ctx.getMethod().getName());
        belezenjeKlicevZrno.zabelezi(ctx.getMethod().getName());

        return ctx.proceed();
    }
}