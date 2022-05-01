package co.tyba.tureserva.excepciones;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import co.tyba.tureserva.modelo.CodigosError;

public class ExcepcionTecnica extends Exception {


    private static final long serialVersionUID = 2576823179834426686L;

    private  String codigo;

    public ExcepcionTecnica(Throwable e) {
        super(e);
        if (e instanceof ExcepcionTecnica) {
            this.codigo = ((ExcepcionTecnica) e).getCodigo();
        } else {
            this.codigo = CodigosError.GENERICO.getCodigo();
        }
    }

   
    public ExcepcionTecnica(String _codigo, Throwable e) {
        super(e);
        this.codigo = _codigo;
    }

    public ExcepcionTecnica(String _codigo, String _mensaje) {
        super(_mensaje);
        this.codigo = _codigo;
    }

    public ExcepcionTecnica(String codigo, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigo = codigo;
    }

    public ExcepcionTecnica(CodigosError error) {
        super(error.getMensaje());
        this.codigo = error.getCodigo();
    }
    

    public ExcepcionTecnica(CodigosError error, Throwable causa) {
        super(error.getMensaje(), causa);
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
