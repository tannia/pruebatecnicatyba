package co.tyba.tureserva.excepciones;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import co.tyba.tureserva.constantes.MsConstantes;
import co.tyba.tureserva.modelo.CodigosError;

public class ExcepcionGenerica extends Exception  {

    private static final long serialVersionUID = 2576823179834426686L;

    private String codigo;


    public ExcepcionGenerica(Throwable e) {
        super(e);
        if (e instanceof ExcepcionGenerica) {
            this.codigo = ((ExcepcionGenerica) e).getCodigo();
        } else {
            this.codigo = CodigosError.GENERICO.getCodigo();
        }
    }

    public ExcepcionGenerica(String _codigo, Throwable e) {
        super(e);
        this.codigo = _codigo;
    }


    public ExcepcionGenerica(String _codigo, String _mensaje) {
        super(_mensaje);
        this.codigo = _codigo;
    }
    

    public ExcepcionGenerica(CodigosError error,Throwable e) {
		super(new StringBuilder(error.getMensaje()).append(MsConstantes.ESPACIO).append(e.getMessage()).toString());
		this.codigo = error.getCodigo();
    }


    public ExcepcionGenerica(CodigosError error) {
        super(error.getMensaje());
        this.codigo = error.getCodigo();
    }


    public String getCodigo() {
        return codigo;
    }


    public String getMensaje() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.getMessage()).append("\n");
        sb.append("<![CDATA[").append("\n");
        Writer writer = new StringWriter();
        PrintWriter ps = new PrintWriter(writer);
        printStackTrace(ps);
        sb.append(writer.toString());
        sb.append("]]>").append("\n");
        return sb.toString();
    } 
    
}
