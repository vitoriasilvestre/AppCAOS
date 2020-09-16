package com.example.my;

import br.ufc.great.caos.api.offload.Offloadable;
import br.ufc.great.syssu.base.Tuple;

public interface IRead {

    //Média da temperatura do usuário
    @Offloadable(Offloadable.Offload.STATIC)
    public String mediaTempAllUsers(String packageName);

    //Média dos batimentos do usuário
    @Offloadable(Offloadable.Offload.STATIC)
    public String mediaBatiUser(String packageName);

    //Média dos dez primeiros batimentos armazenados do usuário
    @Offloadable(Offloadable.Offload.STATIC)
    public String mediaDezPBatiUser(String packageName);

    //Método para procurar user baseado no cpf
    @Offloadable(Offloadable.Offload.STATIC)
    public Tuple getNameCPF(String packagename, String cpf);

    //Método para saber quantas pessoas tem diagnostico
    @Offloadable(Offloadable.Offload.STATIC)
    public int getDiagnostico(String packagename, String diagnostico);

    //Método para saber quantas pessoas tem diagnostico de cancer
    @Offloadable(Offloadable.Offload.STATIC)
    public int getMedication(String packagename, final String medication);

    //Método para saber quantas pessoas tem diagnostico de cancer
    @Offloadable(Offloadable.Offload.STATIC)
    public int getSymptoms(String packagename, final String symptoms);
}
