package servicos.agendamento;

import java.time.LocalTime;

public record AgendamentoParcial(Agendamento agendamento, LocalTime inicio, LocalTime fim) {

}