package com.tapiwa.learn.java.ee;

import com.tapiwa.learn.java.ee.models.BankAccount;
import com.tapiwa.learn.java.ee.services.BankAccountService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/bank-accounts")
public class BankAccountServlet {

    @Inject
    private BankAccountService bankAccountService;



    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<BankAccount> getAllBankAccount(){
       return bankAccountService.getAllBankAccounts();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public  Response createAccount(BankAccount account){
        return Response
                .status(Response.Status.CREATED)
                .entity(bankAccountService.createBankAccount(account))
                .build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneAccount(@PathParam("id") Long id){
        var account = bankAccountService.getOnebankAccount(id);
        if (account !=null){
            return  Response.ok(account).build();

        }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBankAccount(@PathParam("id") Long id, BankAccount updatedAccount){
        var account = bankAccountService.updateBankAccount(id, updatedAccount);
        if (account !=null){
            return  Response.ok(account).build();

        }
        return  Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public  Response deleteBankAccount(@PathParam("id") Long id){

        bankAccountService.deleteBankAccount(id);
        return Response.noContent().build();
    }


}
