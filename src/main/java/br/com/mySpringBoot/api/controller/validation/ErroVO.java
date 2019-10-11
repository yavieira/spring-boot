package br.com.mySpringBoot.api.controller.validation;

public class ErroVO {

    private String campo;
    private String mensagem;

    public ErroVO(String campo, String mensagem){
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
