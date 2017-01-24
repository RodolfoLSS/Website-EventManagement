<%-- 
    Document   : create
    Created on : Sep 29, 2016, 9:36:43 PM
    Author     : RodolfoSaldanha
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/buscaCep.js"></script>
        <title>Inserir Usuário</title>
    </head>
    <body>
        <div class="container">
            
            <h2 class="text-center">Inserção de um novo usuário</h2>
  
            <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/usuario/create">                    
            
            <label class="h4">Nome Completo</label>
            <input class="form-control" type="text" name="fullName" value="${u.fullName}" required autofocus>
                    
            <label class="h4">Foto</label>
            <input class="form-control" type="text" name="photoPath" value="${u.photoPath}" required>
                    
            <label class="h4">CPF</label>
            <input class="form-control" type="text" name="cpf" value="${u.cpf}" required>
                    
            <label class="h4">RG</label>
            <input class="form-control" type="text" name="rg" value="${u.rg}" required>
                    
            <label class="h4">Nome do Crachá</label>
            <input class="form-control" type="text" name="nameTag" value="${u.nameTag}" required>
                    
            <label class="h4">Email</label>
            <input class="form-control" type="text" name="email" value="${u.email}" required>
                    
            <label class="h4">CEP</label>
            <input class="form-control" type="text" name="cep" value="${u.cep}" required onblur="pesquisacep(this.value);" placeholder="Ex.:00000-000" id="cep">
                    
            <label class="h4">Logradouro</label>
-           <input class="form-control" type="text" name="logradouro" value="${u.logradouro}" required id="rua">
-                    
-           <label class="h4">Complemento</label>
-           <input class="form-control" type="text" name="complemento" value="${u.complemento}" required>
-                    
-           <label class="h4">Bairro</label>
-           <input class="form-control" type="text" name="bairro" value="${u.bairro}" required id="bairro">

            <label class="h4">Cidade</label>
            <input class="form-control" type="text" name="city" value="${u.city}" required id="cidade">
                    
            <label class="h4">Estado</label>
            <input class="form-control" type="text" name="state" value="${u.state}" required id="uf">
                    
            <label class="h4">Telefone</label>
            <input class="form-control" type="text" name="tel" value="${u.telResidencial}" placeholder="(DDD)XXXXXXXX" required>
                    
            <label class="h4">Data de Nascimento</label>
            <input class="form-control" type="text" name="dBirth" value="${u.dBirth}" placeholder="DD/MM/AAAA" required>
                    
            <label class="h4">Estado Civil</label>
            <input class="form-control" type="text" name="estadoCivil" value="${u.estadoCivil}" required>
                    
            <label class="h4">Escolaridade</label>
            <input class="form-control" type="text" name="escolaridade" value="${u.escolaridade}" required>
                    
            <label class="h4">Profissão</label>
            <input class="form-control" type="text" name="profession" value="${u.profession}" required>
                    
            <label class="h4">Instituiçao de Origem</label>
            <input class="form-control" type="text" name="instOrigem" value="${u.instOrigem}" required>
                    
            <label class="h4">Tipo do usuário</label>
                    
            <select name="userType">
                <option value='1' ${u.userType == 1 ? 'selected' : ''}>Participante</option>
                <option value='2' ${u.userType == 2 ? 'selected' : ''}>Membro</option>
                <option value='3' ${u.userType == 3 ? 'selected' : ''}>Administrador</option>
            </select>

            <div class="text-center">
                
                <label class="h4">Login</label>
                <input class="form-control" type="text" name="login" value="${u.login}" required>
                    
                <label class="h4">Senha</label>
                <input class="form-control" type="password" name="pwd" required>

                <button class="btn btn-lg btn-primary" type="submit">Inserir</button>
                
                </div>
            </form>

        </div>
                        
    </body>
</html>
