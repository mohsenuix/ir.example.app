package ir.example.app.domain.interactor.base

import ir.example.app.domain.executer.PostExecutionThread
import ir.example.app.domain.executer.UseCaseExecutor
import ir.example.app.domain.repository.Repository
import io.reactivex.Single

/**
 * @param Responses The response value emitted by the Observable.
 * @param Params The request value.
 */
abstract class SingleUseCase<Responses, Params>(useCaseExecutor: UseCaseExecutor,
                                                postExecutionThread: PostExecutionThread,
                                                protected var repository: Repository) :
    UseCase<Single<Responses>, Params>(useCaseExecutor, postExecutionThread) {

    open fun execute(params: Params): Single<Responses> {
        return interact(params).applySchedulers()
    }

    protected abstract fun interact(params: Params): Single<Responses>

}