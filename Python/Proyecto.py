import networkx as nx

# Jose Cifuentes - 17509
# Javier Carpio - 17077
# Hoja de trabajo final
# 26/05/2018

def getCiudadesIngresadas():
    #devuelve las ciudades sin repetir y la cantidad de ciudades sin repetir
    archivo=open("guategrafo.txt","r")
    diccionario={}
    contador=1
    ciudad=""

    for linea in archivo.readlines():
        ciudad=linea[0:linea.find(" ")]
        if (not(ciudad in diccionario)):
            diccionario[ciudad]=contador
            contador=contador+1
            
        linea=linea[linea.find(" ")+1:len(linea)]
        ciudad=linea[0:linea.find(" ")]

        if (not(ciudad in diccionario)):
            diccionario[ciudad]=contador
            contador=contador+1
        
    archivo.close()
    return diccionario,contador

def armarGraph(diccionario):
    g = nx.DiGraph()
    archivo=open("guategrafo.txt","r")
    
    for linea in archivo.readlines():
        ciudadInicio=linea[0:linea.find(" ")]
                   
        linea=linea[linea.find(" ")+1:len(linea)]
        ciudadFin=linea[0:linea.find(" ")]

        linea=linea[linea.find(" ")+1:len(linea)]
        distancia=linea[0:len(linea)]
        
        g.add_edge(diccionario[ciudadInicio],diccionario[ciudadFin],weight=float(distancia))
    return g
    
   
    
    
def getMatriz(cantidadDeCiudades):
    listaGrande=[]
    listaPeque=[]
    for i in range(cantidadDeCiudades):
        listaPeque.append(0)

    for j in range(cantidadDeCiudades):
        listaGrande.append(listaPeque)
        
    return listaGrande

    
    
def getPredecesoresYMatrizDistancias(g,distancia):   
    predecesor, distance = nx.floyd_warshall_predecessor_and_distance(g)
    print ("distancia mas corta: ")
    return predecesor

    

def getRutaMasCorta(inicio,fin,predecesor,diccionarioCiudad):
    inicioNum=0
    finNum=0
    ruta=[]
    

    for i in diccionarioCiudad:
        if(i==inicio):
            inicioNum=diccionarioCiudad[i]

        if(i==fin):
            finNum=diccionarioCiudad[i]
    if((inicioNum==0) or (finNum==0)):
        print("Los datos son incorrectos")

   
    try:
        siguiente=predecesor[inicioNum][finNum]
        ruta.append(siguiente)
    except KeyError:
        print()


        
    while(True):
        try:
            if(predecesor[inicioNum][finNum]==inicio):
                break

            
            siguiente=predecesor[inicioNum][siguiente]
            ruta.append(siguiente)

            if(predecesor[inicioNum][siguiente]==inicio):
                break
        except KeyError:
            break
    contadorRuta=1
    rutaAtomar=[]
    for i in diccionarioCiudad:
        for j in ruta:
            try:
                if(diccionarioCiudad[i]==j):
                    rutaAtomar.append(i)
                contadorRuta=contadorRuta+1
            except IndexError:
                break
    rutaAtomar.append(fin)

    concatenar="La ruta a tomar es: "
    controlConcatenar=0
    for k in rutaAtomar:
        if (controlConcatenar ==0):
            concatenar=concatenar+" "+str(k)
        else:
            concatenar=concatenar+", "+str(k)
        
        controlConcatenar=controlConcatenar+1

    if(len(rutaAtomar)==1):
        print("Datos invalidos")
    else:
        print(concatenar)
        

def armarMenu():
    return "\n 1. Ver la ruta mas corta entre dos ciudades. \n 2. Indicar la ciudad que queda en el centro. \n 3. Modificar grafo. \n 4. Salir."

def getCiudadCentral(g,diccionarioCiudad):
    #Se calcula el ranking segun la centralidad
    ranking = nx.betweenness_centrality(g)
    #Se imprime los nodos mas importantes
    print (ranking)

    lista=[]
    for n in ranking:
        
        if (ranking[n]==0):
            lista.append(n)

    nuevaLista=[]
    for i in diccionarioCiudad:
        for j in lista:
            try:
                if(diccionarioCiudad[i]==j):
                    nuevaLista.append(i)
            except IndexError:
                break

    print(nuevaLista)

    print("Las posibles ciudades centrales pueden ser: ")
    for t in nuevaLista:
        print(t)
        
            
def agregarInterrupcion():
    ciudadInicio=input("Ingrese la ciudad de inicio: ")
    ciudadFinal=input("Ingrese la ciudad de final: ")

    file = open("guategrafo.txt","r")
    lineas = file.readlines()
    file.close()

    

    file = open("guategrafo.txt","w")

    control=0
    for linea in lineas:
        ciudad1=linea[0:linea.find(" ")]
        linea2=linea[linea.find(" ")+1:len(linea)]
        ciudad2=linea2[0:linea.find(" ")]

        if((ciudad1==ciudadInicio) and (ciudad2==ciudadFinal)):
            control=control+1
        else:
            file.write(linea)
    file.close()

    if(control==0):
        print("No se encontraron coincidencias con lo ingresado")
        

def agregarConexion():
    ciudadInicio=input("Ingrese la ciudad de inicio: ")
    ciudadFinal=input("Ingrese la ciudad de final: ")
    distancia=input("Ingrese la distancia entre las ciudades: ")

    if(not(isinstance(distancia, int))):
        print("Distancia ingresada no es numero entero ")
        return ""

    file = open("guategrafo.txt","r")
    lineas = file.readlines()
    file.close()
    

    file = open("guategrafo.txt","w")
    for linea in lineas:
        file.write(linea)
    file.write("\n"+ciudadInicio+" "+ciudadFinal+" "+distancia)
    file.close()
    

##MAIN BUENO
while(True):
    print(armarMenu())
    ingresoOpcion=input("Ingrese la opcion que desea realizar: ")

    if(ingresoOpcion=="1"):
        #para ver ruta más corta
        inicio=input("Ingrese cual es la ciudad de inicio")
        fin=input("Ingrese el destino")
        getRutaMasCorta(inicio,fin,predecesor,diccionarioCiudades)
        
    if(ingresoOpcion=="2"):
        getCiudadCentral(armarGraph(diccionarioCiudades),diccionarioCiudades)

    if(ingresoOpcion=="3"):
        print("a) Ingresar interrupcion entre dos ciudades")
        print("b) Ingrese nueva coneccion entre dos ciudades")
        subSeleccion=input("Ingrese la opcion")
        
        if(subSeleccion=="a"):
            agregarInterrupcion()
        if(subSeleccion=="b"):
            agregarConexion()
            
    if(ingresoOpcion=="4"):
        break
    
