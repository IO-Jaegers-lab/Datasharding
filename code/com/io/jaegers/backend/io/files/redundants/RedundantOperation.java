package com.io.jaegers.backend.io.files.redundants;

import com.io.jaegers.backend.io.files.FileSystemFlag;
import com.io.jaegers.backend.io.files.FileSystemOperation;
import com.io.jaegers.backend.io.files.algorithms.BreathFirstTraversal;
import com.io.jaegers.backend.io.files.algorithms.component.FoundConnector;
import com.io.jaegers.backend.io.files.algorithms.interfaces.TraversalFacade;


public class RedundantOperation
    implements FileSystemOperation
{
    public RedundantOperation()
    {
        this.setFacade( new AlgorithmFacade() );
    }

    public RedundantOperation( String pathToDirectory )
    {
        this();

        this.getFacade().setRootPath( pathToDirectory );
        this.traversal = new BreathFirstTraversal( this.getFacade() );
    }


    protected BreathFirstTraversal preparation()
    {
        if( this.isTraversalNull() )
        {
            this.setTraversal(
                    new BreathFirstTraversal(
                            this.getFacade()
                    )
            );
        }

        return this.getTraversal();
    }

    // Variables
    private BreathFirstTraversal traversal = null;

    private AlgorithmFacade facade = null;


    @Override
    public void execute()
    {
        if( this.getFacade().isSet() )
        {
            BreathFirstTraversal traversal = this.preparation();

            traversal.setConnector(
                    new FoundConnector(
                            new RedundantOnFilesFound(),
                            new RedundantOperationSettings()
                    )
            );

            this.setTraversal( traversal );
        }

        this.traversal.execute();
    }

    @Override
    public String getOperationName()
    {
        return "Identify Redundant Files";
    }


    @Override
    public void setFlags( FileSystemFlag flag, String label )
    {

    }

    public AlgorithmFacade getFacade()
    {
        return this.facade;
    }


    public void setFacade( AlgorithmFacade facade )
    {
        this.facade = facade;
    }

    public boolean isTraversalNull()
    {
        return ( this.traversal == null );
    }


    public BreathFirstTraversal getTraversal()
    {
        return this.traversal;
    }

    public void setTraversal( BreathFirstTraversal traversal )
    {
        this.traversal = traversal;
    }

    // Appendices
    protected static class AlgorithmFacade
        implements TraversalFacade
    {
        public AlgorithmFacade()
        {
            this.path = null;
        }

        public AlgorithmFacade( String path )
        {
            this.setRootPath( path );
        }

        private String path;

        public void setRootPath( String rootPath )
        {
            this.path = rootPath;
        }

        @Override
        public String getRootPath()
        {
            return this.path;
        }

        public boolean isNull()
        {
            return this.path == null;
        }

        public boolean isSet()
        {
            return !this.isNull();
        }

        @Override
        public String toString()
        {
            return super.toString();
        }
    }

    public static void main( String[] args )
    {
        RedundantOperation operation = new RedundantOperation("C:\\Workspace\\common hardware initiative\\Archive\\dataset");
        operation.execute();


    }
}
