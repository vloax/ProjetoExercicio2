package com.exercicio.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cachorro {
   private int id;
   private String nome;
   private String raca;
   private LocalDate dataNascimento;

   public Cachorro() {
   }

   public Cachorro(String nome, String raca, LocalDate dataNascimento) {
      this.nome = nome;
      this.raca = raca;
      this.dataNascimento = dataNascimento;
   }

   public Cachorro(int id, String nome, String raca, LocalDate dataNascimento) {
      this.id = id;
      this.nome = nome;
      this.raca = raca;
      this.dataNascimento = dataNascimento;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getRaca() {
      return raca;
   }

   public void setRaca(String raca) {
      this.raca = raca;
   }

   public LocalDate getDataNascimento() {
      return dataNascimento;
   }

   public void setDataNascimento(LocalDate dataNascimento) {
      this.dataNascimento = dataNascimento;
   }

   @Override
   public String toString() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return "" + id + " - " + nome + " - Raça: " + raca + " - Data de Nascimento: " + dataNascimento.format(formatter);
   }
}
