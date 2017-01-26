package gwt.react.mobx.todo.client.components;

import gwt.interop.utils.shared.collections.Array;
import gwt.mobx.client.MobXReact;
import gwt.react.client.api.React;
import gwt.react.client.components.StatelessComponent;
import gwt.react.client.elements.ReactElement;
import gwt.react.client.proptypes.BaseContext;

import static gwt.react.client.api.GwtReact.castAsReactElement;
import static gwt.react.client.api.React.DOM.ul;
import static gwt.react.mobx.todo.client.components.Todo.todo;

class TodoList {

    private static StatelessComponent<AppStateProps, BaseContext> component = MobXReact.observer((props, context) -> {
        Array<ReactElement<?, ?>> todoEls = props.appState.getVisibleTodos().map((todo) -> {
            Todo.Props p = new Todo.Props();
            p.key = Integer.toString(todo.id);
            p.onClickToToggle = () -> props.appState.toggleTodo(todo.id);
            p.text = todo.text;
            p.completed = todo.completed;

            return todo(p);
        });

        return ul(null,
            castAsReactElement(todoEls));
    });

    static ReactElement<AppStateProps, ?> todoList(AppStateProps props) {
        return React.createElement(component, props);
    }
}