package pt.isel.ls.utils.interfaces;

import pt.isel.ls.utils.exceptions.InvalidPathMethodParameterException;

import java.util.ArrayList;

public interface IPathMethod {
    String generateCondition(ArrayList<String> specialCommandParameters, String SQL) throws InvalidPathMethodParameterException;
}
